package com.application.kgtuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.kgtuapp.Classes.ScheduleDay
import com.application.kgtuapp.Fragmets.ScheduleDayFragment
import com.application.kgtuapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tryButton.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.contentLayout, ScheduleDayFragment.newInstance())
                .commit()
        }

        /*binding.mainMenuButtonSchedule
            .setOnclickListener{

        }*/

        /*supportFragmentManager
            .beginTransaction()
            .replace(R.id.contentLayout, ScheduleDayFragment.newInstance())
            .commit()*/


    }

    /*showFragmentButton.*/

    val day = ScheduleDay(0,"18-ВТ", 1)


}

