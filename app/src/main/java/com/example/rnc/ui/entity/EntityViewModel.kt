package com.example.rnc.ui.entity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rnc.data.EntityRepo
import com.example.rnc.domain.ReligiousEntity
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EntityViewModel @ViewModelInject constructor(private val repo: EntityRepo)
    : ViewModel(), EntityMVVM.ViewModel{

    override fun searchData(entity: ReligiousEntity) = viewModelScope.launch {
        repo.getAdditionalData(entity)
    }
}
