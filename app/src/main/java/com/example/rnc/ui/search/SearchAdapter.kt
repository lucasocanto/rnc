package com.example.rnc.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rnc.R
import com.example.rnc.domain.ReligiousEntity
import kotlinx.android.synthetic.main.item_entity.view.*

class SearchAdapter(private val listener: EntityListener) : RecyclerView.Adapter<ViewHolder>(){

    var list: List<ReligiousEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_entity, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener { listener.onClick(list[position]) }
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetTextI18n")
    fun onBind(item: ReligiousEntity?){
        itemView.item_name_main.text = item?.name
        itemView.item_ci_main.text = item?.ci
        itemView.item_address_main.text = item?.address
        itemView.item_location_main.text = ", ${item?.city}, ${item?.province}"
    }
}

interface EntityListener{
    fun onClick(entity: ReligiousEntity)
}