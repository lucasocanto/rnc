package com.example.rnc.ui.entity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.rnc.R
import com.example.rnc.domain.ReligiousEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_entity.*

@AndroidEntryPoint
class EntityActivity : AppCompatActivity(), EntityMVVM.View {

    private lateinit var entity: ReligiousEntity

    private val viewModel: EntityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entity)
        entity = intent.getSerializableExtra("entity") as ReligiousEntity
        viewModel.searchData(entity)
        setBasicData()
        setAdditionalData()
    }

    override fun getIntent(context: Context) = Intent(context, EntityActivity::class.java)

    @SuppressLint("SetTextI18n")
    override fun setBasicData() {
        name_entity.text = entity.name
        ci_entity.text = entity.ci
        address_entity.text = entity.address
        location_entity.text = "${entity.city}, ${entity.province}"
    }

    override fun setAdditionalData() {
        if (entity.additionalData) {
            authority_entity.text =entity.authority
            type_entity.text = entity.type
            mail_entity.text = entity.mail
            phone_entity.text = entity.phone
            zip_code_entity.text = entity.zipCode
        } else showToast(R.string.cant_find_additional_data)
    }

    override fun showToast(message: Int) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }
}