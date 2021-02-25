package com.example.rnc.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rnc.data.EntityRepo
import com.example.rnc.domain.ReligiousEntity
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.InputStream

class SearchViewModel
    @ViewModelInject constructor(private val repo: EntityRepo): ViewModel(), SearchMVVM.ViewModel {

    private var _list = MutableLiveData<List<ReligiousEntity>>()
        .apply { value = emptyList() }

    var list: LiveData<List<ReligiousEntity>> = _list

    override fun initializeDatabase(inputStream: InputStream): Job = viewModelScope.launch {
        repo.readData(inputStream)
    }

    override fun find(filter: String, query: String): Job = viewModelScope.launch{
        repo.getResults(filter, query)
        _list.value = repo.list
    }
}