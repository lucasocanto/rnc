package com.example.rnc.ui.entity

import android.content.Context
import android.content.Intent
import com.example.rnc.domain.ReligiousEntity
import kotlinx.coroutines.Job

interface EntityMVVM {
    interface Model{
        fun setAdditionalData(row: Array<String>)
    }
    interface View{
        fun getIntent(context: Context) : Intent
        fun setBasicData()
        fun setAdditionalData()
        fun showToast(message: Int)
    }
    interface ViewModel{
        fun searchData(entity: ReligiousEntity) : Job
    }
}