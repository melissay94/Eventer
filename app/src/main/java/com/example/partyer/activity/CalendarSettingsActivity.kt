package com.example.partyer.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.partyer.R
import com.example.partyer.viewmodel.CalendarSettingViewModel

class CalendarSettingsActivity: BaseActivity() {

    lateinit var calendarSettingsViewModel: CalendarSettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_settings)
    }

    override fun initializeViewModel() {
        calendarSettingsViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CalendarSettingViewModel::class.java)
    }

}