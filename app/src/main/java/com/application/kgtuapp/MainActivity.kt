package com.application.kgtuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.kgtuapp.Classes.ScheduleDay

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    val day = ScheduleDay(0,"18-ВТ", 1)
}

