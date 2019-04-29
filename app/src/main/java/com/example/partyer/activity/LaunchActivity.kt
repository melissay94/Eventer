package com.example.partyer.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.partyer.R
import com.example.partyer.viewmodel.LaunchViewModel
import kotlinx.android.synthetic.main.activity_launch.*

class LaunchActivity: BaseActivity() {

    lateinit var launchViewModel: LaunchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        launchMainActivity()
    }

    override fun initializeViewModel() {
        launchViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LaunchViewModel::class.java)
    }

    private fun launchMainActivity() {
        app_logo.visibility = View.VISIBLE

        app_logo.animate()
            .alpha(1f)
            .setListener(null)
            .duration = 250

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1000)
    }
}