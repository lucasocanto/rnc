package com.example.rnc.data

import com.opencsv.CSVReader
import java.io.InputStream

class CsvDatabaseProcessor {

    fun readEntitiesData(inputStream: InputStream): MutableList<Array<String>> {
        val reader = CSVReader(inputStream.bufferedReader())
        return reader.readAll()
    }
}
