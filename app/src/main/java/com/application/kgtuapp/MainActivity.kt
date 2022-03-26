package com.application.kgtuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.application.kgtuapp.Classes.ScheduleDay
import com.application.kgtuapp.ViewModels.DataModel
import com.application.kgtuapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val dataModel: DataModel by viewModels()

    /*var studyGroup: String? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataModel.isUserAutorized.value = true

        if (dataModel.isUserAutorized.value!!){
            setContentView(R.layout.fragment_info)
            binding = ActivityMainBinding.inflate(layoutInflater)
            //Устаревший подход, надо переписать
            val handler = Handler()
            handler.postDelayed({
                // do something after 2000ms
                setContentView(binding.root)
            }, 2000)
        } else {
            /*openFragment(R.id.l_mainActivityFragment, )*/
        }

        //openFragment(R.id.contentLayout, InfoFragment.newInstance())
        //openFragment(R.id.mainMenuContainer, MainMenuFragment.newInstance())

        //Про view model важный код
        dataModel.mainToolBarTitle.observe(this, {
            binding.mainToolBar.setTitle(it)
        })

        /*dataModel.studyGroup.observe(this, {
            val tv_userGroup = findViewById<TextView>(R.id.tv_scheduleUserStudyGroupBody)
            tv_userGroup.text = it
        })*/

        /*binding.tryButton.setOnClickListener {
            openFragment(R.id.contentContainer, ScheduleDayFragment.newInstance())
        }*/

    }

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

