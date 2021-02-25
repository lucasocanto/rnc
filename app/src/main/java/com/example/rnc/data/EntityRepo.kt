package com.example.rnc.data

import com.example.rnc.domain.ReligiousEntity
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

class EntityRepo {

    var list = ArrayList<ReligiousEntity>()
    private var database = LinkedList<Array<String>>()
    private var parallelDatabase = LinkedList<Array<String>>()  // NOT IMPLEMENTED YET

    fun readData(inputStream: InputStream) {
        database = CsvDatabaseProcessor().readEntitiesData(inputStream) as LinkedList<Array<String>>
    }

    fun getResults(filter: String, query: String) {
        list.clear()
        for (row in database) when(filter) {
            "Nombre" -> checkForSimilar(row, query, row[0])
            "Dirección" -> checkForSimilar(row, query, row[1])
            "Ciudad" -> checkForEqual(row, query, row[2])
            "Provincía" -> checkForEqual(row, query, row[3])
            "CI" -> checkForEqual(row, query, row[4])
        }
    }

    fun getAdditionalData(entity: ReligiousEntity) {
        for (row in parallelDatabase) if (checkEntityData(row, entity)) entity.setAdditionalData(row)
    }

    private fun checkEntityData(row: Array<String>, entity: ReligiousEntity) : Boolean =
         row[0] == entity.name && row[1] == entity.address && row[4] == entity.ci

    private fun checkForEqual(row: Array<String>, query: String, where: String){
        if (query.trim().toLowerCase(Locale.ROOT) == (where.trim().toLowerCase(Locale.ROOT)))
            getEntity(row)
    }

    private fun checkForSimilar(row: Array<String>, query: String, where: String){
        if(where.trim().toLowerCase(Locale.ROOT).contains(query.trim().toLowerCase(Locale.ROOT)))
            getEntity(row)
    }

    private fun getEntity(row: Array<String>){
        val entity =  ReligiousEntity()
        entity.parseRow(row)
        list.add(entity)
    }
}