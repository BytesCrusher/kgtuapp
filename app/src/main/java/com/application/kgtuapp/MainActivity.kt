package com.application.kgtuapp

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.application.kgtuapp.Classes.ScheduleDay
import com.application.kgtuapp.Fragments.*
import com.application.kgtuapp.ViewModels.DataModel
import com.application.kgtuapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val dataModel: DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataModel.isUserAutorized.value = false

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openFragment(R.id.l_mainActivityFragment, InfoFragment.newInstance())


        /*setContentView(R.layout.fragment_info)
        binding = ActivityMainBinding.inflate(layoutInflater)
            //Устаревший подход, надо переписать
        val handler = Handler()
        handler.postDelayed({
                // do something after 2000ms
                setContentView(binding.root)
                openFragment(R.id.l_mainActivityFragment, MainActivityContentFragment.newInstance())
                            }, 2000)*/

            //а это костыль
            /*setContentView(R.layout.fragment_autorization)*/

            //должно открываться примерно вот так
            /*openFragment(R.id.l_mainActivityFragment, AuthorisationFragment.newInstance())*/

            //костыльная иммитация работы

            /*openFragment(R.id.l_mainActivityFragment, AuthorisationFragment.newInstance())*/


        //openFragment(R.id.contentLayout, InfoFragment.newInstance())
        //openFragment(R.id.mainMenuContainer, MainMenuFragment.newInstance())

        /*//Про view model важный код
        dataModel.mainToolBarTitle.observe(this, {
            *//*binding.mainToolBar.setTitle(it)*//*
        })

        //Про view model важный код
        dataModel.mainToolBarTitle.observe(UniversityFragmentObject*//*MainActivityContentFragment.newInstance()*//*, {
            val a = MainActivityContentFragment.newInstance()
            a.binding.fcMainToolBar.setTitle(it)
        })

        dataModel.studyGroup.observe(ProfileFragment.newInstance(), {

        })*/

        /*dataModel.studyGroup.observe(this, {
            val tv_userGroup = findViewById<TextView>(R.id.tv_scheduleUserStudyGroupBody)
            tv_userGroup.text = it
        })*/

        /*binding.tryButton.setOnClickListener {
            openFragment(R.id.contentContainer, ScheduleDayFragment.newInstance())
        }*/

    }

    companion object {
        @JvmStatic
        fun newInstance() = MainActivity()
    }

    //id объекта, где показать новый фрагмент, сам новый фрагмент
    //R.id.mainMenuContainer, MainMenuFragment.newInstance()
    private fun openFragment(idHolder: Int, newFragment:Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder, newFragment)
            .commit()
    }
}

