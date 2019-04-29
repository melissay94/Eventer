package com.example.partyer.data

import com.google.gson.stream.JsonReader
import org.json.JSONObject
import java.io.File
import javax.inject.Singleton

@Singleton
class PartyRepository(
    private val readFile: ReadFile,
    private val writeFile: WriteFile) {

    fun createNewUserFile(filesDir: File) {
        writeFile.createNewUserFile(filesDir)
    }

    fun getPartyInfo(): List<PartyItem> = readFile.readJsonString(PARTY_DATA)

    fun getUserInfo(jsonReader: JsonReader): List<PartyItem> = readFile.readJsonFile(jsonReader)

    fun createPartyJSONObject(partyItem: PartyItem): JSONObject {
        return writeFile.writeJSONObject(
            partyItem.name,
            partyItem.description,
            partyItem.startDate,
            partyItem.endDate
        )
    }

    fun savePartyObject(partyObject: JSONObject, userPartyList: List<PartyItem>, filesDir: File) {
        writeFile.writeToFile(partyObject, userPartyList, filesDir)
    }

    fun removePartyObject(userPartyList: List<PartyItem>, filesDir: File) {
        writeFile.removeFromFile(userPartyList, filesDir)
    }
}