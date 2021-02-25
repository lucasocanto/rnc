package com.example.rnc.ui.search

import kotlinx.coroutines.Job
import java.io.InputStream

interface SearchMVVM{
    interface Model{
        fun parseRow(row: Array<String>)
    }
    interface View{
        fun initializeRecyclerView()
        fun displayFilters()
        fun search()
        fun showToast(message: Int)
    }
    interface ViewModel{
        fun initializeDatabase(inputStream: InputStream) : Job
        fun find(filter: String, query: String): Job
    }
}

