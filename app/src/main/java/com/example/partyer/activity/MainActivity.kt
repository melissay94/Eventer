package com.example.partyer.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.JsonReader
import com.example.partyer.R
import com.example.partyer.data.PartyItem
import com.example.partyer.data.USER_FILE_NAME
import com.example.partyer.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var partyData: List<PartyItem>

    private var currentCardIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViewModel()

        partyData = mainViewModel.getPartyInfo()
        mainViewModel.createNewUserFile(this.filesDir)

        updateEvent()

        party_list.setOnClickListener {
            startActivity(Intent(this, PartyListActivity::class.java))
        }

        calendar_setting.setOnClickListener {
            startActivity(Intent(this, CalendarSettingsActivity::class.java))
        }

        no_rsvp_button.setOnClickListener {
            if (currentCardIndex >= 0 && currentCardIndex < partyData.size) {
                currentCardIndex += 1
                updateEvent()
            } else {
                yes_rsvp_button.isEnabled = false
                no_rsvp_button.isEnabled = false
                displayNoMoreParties()
            }
        }

        yes_rsvp_button.setOnClickListener {
            if (currentCardIndex >= 0 && currentCardIndex < partyData.size) {
                addEventToUserInfo(partyData[currentCardIndex])
                currentCardIndex += 1
                updateEvent()
            } else {
                yes_rsvp_button.isEnabled = false
                no_rsvp_button.isEnabled = false
                displayNoMoreParties()
            }
        }
    }

    override fun initializeViewModel() {
        mainViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)
    }

    private fun updateEvent() {

        if (currentCardIndex < partyData.size) {
            card_title.text = partyData[currentCardIndex].name
            card_description.text = partyData[currentCardIndex].description

            val startDate = SimpleDateFormat("EEE, MMM d 'at' h:mm aaa", Locale.getDefault()).format(partyData[currentCardIndex].startDate)
            card_start_date.text = startDate

            val endDate = SimpleDateFormat("EEE, MMM d 'at' h:mm aaa", Locale.getDefault()).format(partyData[currentCardIndex].endDate)
            card_end_date.text = endDate
        }
    }

    private fun displayNoMoreParties() {
        card_title.text = getString(R.string.end_of_parties_title)
        card_description.text = getString(R.string.end_of_parties_description)
        card_start_date.text = ""
        card_end_date.text = ""
    }

    private fun addEventToUserInfo(partyItem: PartyItem) {

        val userFile = openFileInput(USER_FILE_NAME)
        mainViewModel.addEventToUserData(partyItem, userFile, this.filesDir)
    }

    private fun getNewParties(): List<PartyItem> {
        val currentParties = mainViewModel.getPartyInfo()
        val inputReader = InputStreamReader(openFileInput(USER_FILE_NAME), "UTF-8")
        val userPartyData = mainViewModel.getUserPartyInfo(com.google.gson.stream.JsonReader(inputReader))

        currentParties.filter { party ->

        }
    }
}
