package com.example.myapplication.ui.error

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.ui.onboarding.OnboardingActivity
import com.google.android.material.button.MaterialButton


class NoInternetActivity : AppCompatActivity() {
    private lateinit var button: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_no_internet)
        checkConnection()
    }


    private fun checkConnection() {
        button = findViewById(R.id.btnRetry)
        button.setOnClickListener {
            if (isOnline()) {
                val i = Intent(this, OnboardingActivity::class.java)
                startActivity(i)
            }
        }
    }
}
