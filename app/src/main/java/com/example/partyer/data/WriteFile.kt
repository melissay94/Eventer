package com.example.partyer.data

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.lang.Exception
import java.util.*

class WriteFile: JSONObject() {

    fun createNewUserFile(filesDir: File) {
        val userFile = File(filesDir, USER_FILE_NAME)
        if (!userFile.exists()) {
            userFile.createNewFile()
        }
    }

    fun writeJSONObject(title: String, description: String, startDate: Date, endDate: Date): JSONObject {

        val jsonInfo = JSONObject()

        val startDateString = expectedDateFormat.format(startDate)
        val endDateString = expectedDateFormat.format(endDate)

        try {
            jsonInfo.put("name", title)
            jsonInfo.put("description", description)
            jsonInfo.put("startDate", startDateString)
            jsonInfo.put("endDate", endDateString)
        } catch (exception: JSONException) {
            Log.e("writeFile", exception.message)
        }

        return jsonInfo
    }

    fun writeToFile(party: JSONObject, currentUserFile: List<PartyItem>, filesDir: File) {
        try {
            val writer: Writer?
            val file = File(filesDir, USER_FILE_NAME)
            val jsonArray = JSONArray()
            writer = BufferedWriter(FileWriter(file))

            if (file.exists()) {
                currentUserFile.forEach {
                    jsonArray.put(
                        writeJSONObject(it.name, it.description, it.startDate, it.endDate)
                    )
                }
                jsonArray.put(party)
            } else {
                jsonArray.put(party)
            }

            writer.write(jsonArray.toString())
            writer.close()
        } catch (exception: Exception) {
            Log.e("writeFile", "Couldn't add item: ${exception.message}")
        }
    }

    fun removeFromFile(currentUserFile: List<PartyItem>, filesDir: File) {
        try {
            val writer: Writer?
            val file = File(filesDir, USER_FILE_NAME)
            val jsonArray = JSONArray()
            writer = BufferedWriter(FileWriter(file))

            if (file.exists()) {
                currentUserFile.forEach { item ->
                    jsonArray.put(
                        writeJSONObject(item.name, item.description, item.startDate, item.endDate)
                    )
                }
            }
            writer.write(jsonArray.toString())
            writer.close()
        } catch (exception: Exception) {
            Log.e("writeFile", "Couldn't remove item: ${exception.message}")
        }
    }
}