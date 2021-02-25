package com.example.rnc.domain

import com.example.rnc.ui.entity.EntityMVVM
import com.example.rnc.ui.search.SearchMVVM
import java.io.Serializable

data class ReligiousEntity(
        var ci: String,
        var name: String,
        var address: String,
        var city: String,
        var province: String,
        var zipCode: String?,
        var mail: String?,
        var phone: String?,
        var authority: String?,
        var type: String?,
        var additionalData: Boolean
): Serializable, SearchMVVM.Model, EntityMVVM.Model {

    constructor() : this(
            "",
            "",
            "",
            "",
            "",
            null,
            null,
            null,
            null,
            null,
    false)

    override fun parseRow(row: Array<String>) {
        name = row[0]
        address = row[1]
        city = row[2]
        province = row[3]
        ci = row[4]
    }

    override fun setAdditionalData(row: Array<String>) {
        additionalData = true
        zipCode = row[5]
        mail = row[6]
        phone = row[7]
        authority = row[8]
        type = row[9]
    }
}