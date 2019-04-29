package com.example.partyer.data

import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import java.text.SimpleDateFormat
import java.util.*

class ReadFile {

    fun readJsonString(jsonString: String): List<PartyItem> {

        val gson = GsonBuilder().create()
        val partyList = gson.fromJson(jsonString, Array<PartyDataItem>::class.java)

        return partyList.map { it.toPartyItem()  }
    }

    fun readJsonFile(jsonReader: JsonReader): List<PartyItem> {

        val gson = GsonBuilder().create()
        val partyList: Array<PartyDataItem>  = gson.fromJson(jsonReader, Array<PartyDataItem>::class.java)

        return partyList.map { it.toPartyItem() }
    }
}