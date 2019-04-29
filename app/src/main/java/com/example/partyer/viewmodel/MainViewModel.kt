package com.example.partyer.viewmodel

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.partyer.data.PartyItem
import com.example.partyer.data.PartyRepository
import com.example.partyer.data.ReadFile
import com.example.partyer.data.WriteFile
import com.google.gson.stream.JsonReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject

class MainViewModel @Inject constructor(
    readFile: ReadFile,
    writeFile: WriteFile
): ViewModel() {

    private val partyRepository = PartyRepository(readFile, writeFile)

    fun getPartyInfo() = partyRepository.getPartyInfo()

    fun getUserPartyInfo(jsonReader: JsonReader) = partyRepository.getUserInfo(jsonReader)

    fun createNewUserFile(filesDir: File) {
        partyRepository.createNewUserFile(filesDir)
    }

    fun addEventToUserData(partyItem: PartyItem, inputStream: InputStream, filesDir: File) {

        val writeObj = partyRepository.createPartyJSONObject(partyItem)

        val userFile = try {
            val jsonReader = JsonReader(InputStreamReader(inputStream, "UTF-8"))
            partyRepository.getUserInfo(jsonReader)
        } catch (exception: Exception) {
            Log.e("readFile", "Could not read file")
            emptyList<PartyItem>()
        }

        partyRepository.savePartyObject(writeObj, userFile, filesDir)
    }
}
