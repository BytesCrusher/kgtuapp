package com.application.kgtuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.application.kgtuapp.Classes.ScheduleDay
import com.application.kgtuapp.ViewModels.DataModel
import com.application.kgtuapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val dataModel: DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(R.layout.fragment_info)

        val handler = Handler()
        handler.postDelayed({
            // do something after 1000ms
            setContentView(binding.root)
        }, 3000)





        /*fun changeToolbarTitle(newTitle: String){
            binding.mainToolBar.setTitle(newTitle)
        }*/

        //openFragment(R.id.contentLayout, InfoFragment.newInstance())
        //openFragment(R.id.mainMenuContainer, MainMenuFragment.newInstance())

        //Про view model важный код
        dataModel.mainToolBarTitle.observe(this, {
            binding.mainToolBar.setTitle(it)
        })

        /*binding.tryButton.setOnClickListener {
            openFragment(R.id.contentContainer, ScheduleDayFragment.newInstance())
        }*/

    }

    /*showFragmentButton.*/

    val day = ScheduleDay(0,"18-ВТ", 1)


    //id объекта, где показать новый фрагмент, сам новый фрагмент
    //R.id.mainMenuContainer, MainMenuFragment.newInstance()
    private fun openFragment(idHolder: Int, newFragment:Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder, newFragment)
            .commit()
    }

}

