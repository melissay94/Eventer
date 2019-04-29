package com.example.partyer.data

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

val expectedDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

data class PartyDataItem (
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("startDate") val startDate: String? = null,
    @SerializedName("endDate") val endDate: String? = null
) {

    fun toPartyItem(): PartyItem {
        val startDateToDate = expectedDateFormat.parse(startDate)
        val endDateToDate = expectedDateFormat.parse(endDate)

        return PartyItem(
            id = id ?: -1,
            name = name.orEmpty(),
            description = description.orEmpty(),
            startDate = startDateToDate,
            endDate = endDateToDate
        )
    }
}

data class PartyItem (
    val id: Int = -1,
    val name: String = "",
    val description: String = "",
    val startDate: Date = Date(0),
    val endDate: Date = Date(0)
)