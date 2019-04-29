package com.example.partyer.activity

import android.Manifest
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.example.partyer.R
import com.example.partyer.viewmodel.CalendarPromptViewModel
import kotlinx.android.synthetic.main.activity_calendar_prompt.*

class CalendarPromptActivity: BaseActivity() {

    private lateinit var calendarPromptViewModel: CalendarPromptViewModel

    private val requestCalPermissions = 8008

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_prompt)

        accept_permissions.setOnClickListener {
            shareCalendar()
        }
        decline_permissions.setOnClickListener {
            skipCalendarPermissions()
        }
    }

    override fun initializeViewModel() {
        calendarPromptViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CalendarPromptViewModel::class.java)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == requestCalPermissions) {
            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {

            }
        }
    }

    private fun shareCalendar() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR),
            requestCalPermissions
        )
    }

    private fun skipCalendarPermissions() {
        finish()
    }

    private fun onPermissionsGranted() {
        if (checkPermissions( Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR)) {

        }
    }

    private fun checkPermissions(vararg permissions: String): Boolean {
        val permissionDenied = permissions.any {permission ->
            ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED
        }

        return !permissionDenied
    }


}