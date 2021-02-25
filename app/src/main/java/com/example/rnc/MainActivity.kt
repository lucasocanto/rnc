package com.example.rnc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rnc.domain.ReligiousEntity
import com.example.rnc.ui.entity.EntityActivity
import com.example.rnc.ui.search.EntityListener
import com.example.rnc.ui.search.SearchMVVM
import com.example.rnc.ui.search.SearchAdapter
import com.example.rnc.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_filters.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchMVVM.View, EntityListener {

    private lateinit var adapter: SearchAdapter
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.initializeDatabase(this.resources.openRawResource(R.raw.rnc))
        initializeRecyclerView()
        displayFilters()
        search()
    }

    override fun initializeRecyclerView() {
        adapter = SearchAdapter(this)
        main_recycler.adapter = adapter
        main_recycler.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        main_recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
    }

    @SuppressLint("InflateParams")
    override fun displayFilters() {
        filters_button.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
                    .setView(LayoutInflater.from(this).inflate(R.layout.dialog_filters, null))
                    .create()
            dialog.setTitle(R.string.filters)
            dialog.show()
            setFiltersListeners(dialog)
        }
    }

    private fun setFiltersListeners(dialog: AlertDialog) {
        val filters = ArrayList<TextView?>()
        filters.add(dialog.ci_filter)
        filters.add(dialog.name_filter)
        filters.add(dialog.address_filter)
        filters.add(dialog.city_filter)
        filters.add(dialog.province_filter)
        filters.forEach { onFilterClicked(it,dialog) }
    }

    private fun onFilterClicked(filter: TextView?, dialog: AlertDialog) {
        filter?.setOnClickListener {
            dialog.dismiss()
            filters_button.text = filter.text.toString()
        }
    }

    override fun search() {
        search_button.setOnClickListener {
            when {
                edit_search.text.isBlank() ->
                    showToast(R.string.write_something)
                filters_button.text.toString() == "Filtros" ->
                    showToast(R.string.select_filter)
                else ->
                    viewModel.find(filters_button.text.toString(), edit_search.text.toString())
                }
            refreshList()
            return@setOnClickListener
        }
    }

    private fun refreshList(){
        viewModel.list.observe(this, Observer {
            adapter.list  = it
            adapter.notifyDataSetChanged()
        })
        if (adapter.list.isEmpty()) showToast(R.string.cant_find_results)
    }

    override fun showToast(message: Int) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(entity: ReligiousEntity) {
        val intent = EntityActivity().getIntent(this)
        intent.putExtra("entity", entity)
        startActivity(intent)
    }
}