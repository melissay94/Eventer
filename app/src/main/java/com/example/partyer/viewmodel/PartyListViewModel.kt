package com.example.partyer.viewmodel

import android.arch.lifecycle.ViewModel
import com.example.partyer.data.PartyItem
import com.example.partyer.data.ReadFile
import com.example.partyer.data.WriteFile
import com.google.gson.stream.JsonReader
import java.io.File
import javax.inject.Inject

class PartyListViewModel @Inject constructor(
    private val readFile: ReadFile,
    private val writeFile: WriteFile
): ViewModel() {

    fun getUserPartyData(jsonReader: JsonReader): List<PartyItem> {
        return readFile.readJsonFile(jsonReader)
    }

    fun removePartyFromList(currentUserFile: List<PartyItem>, fileDir: File) {
        return writeFile.removeFromFile(currentUserFile, fileDir)
    }
}