package com.application.kgtuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.application.kgtuapp.Classes.ScheduleDay
import com.application.kgtuapp.Fragments.AuthorisationFragment
import com.application.kgtuapp.Fragments.MainActivityContentFragment
import com.application.kgtuapp.Fragments.ProfileFragment
import com.application.kgtuapp.Fragments.UniversityFragment
import com.application.kgtuapp.ViewModels.DataModel
import com.application.kgtuapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var UniversityFragmentObject:Fragment

    private val dataModel: DataModel by viewModels()

    /*var studyGroup: String? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*APP_ACTIVITY = this*/

        dataModel.isUserAutorized.value = true

        UniversityFragmentObject = UniversityFragment.newInstance()

        if (dataModel.isUserAutorized.value!!){
            setContentView(R.layout.fragment_info)
            binding = ActivityMainBinding.inflate(layoutInflater)
            //Устаревший подход, надо переписать
            val handler = Handler()
            handler.postDelayed({
                // do something after 2000ms
                setContentView(binding.root)
                openFragment(R.id.l_mainActivityFragment, MainActivityContentFragment.newInstance())
            }, 2000)
        } else {
            //а это костыль
            /*setContentView(R.layout.fragment_autorization)*/

            //должно открываться примерно вот так
            /*openFragment(R.id.l_mainActivityFragment, AuthorisationFragment.newInstance())*/

            //костыльная иммитация работы
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            /*openFragment(R.id.l_mainActivityFragment, AuthorisationFragment.newInstance())*/
            openFragment(R.id.l_mainActivityFragment, AuthorisationFragment.newInstance())
            /*setContentView(R.layout.activity_main)*/
            /*openFragment(R.id.l_mainActivityFragment, AuthorisationFragment.newInstance())*/
        }

        //openFragment(R.id.contentLayout, InfoFragment.newInstance())
        //openFragment(R.id.mainMenuContainer, MainMenuFragment.newInstance())

        //Про view model важный код
        dataModel.mainToolBarTitle.observe(this, {
            /*binding.mainToolBar.setTitle(it)*/
        })

        //Про view model важный код
        dataModel.mainToolBarTitle.observe(UniversityFragmentObject/*MainActivityContentFragment.newInstance()*/, {
            val a = MainActivityContentFragment.newInstance()
            a.binding.fcMainToolBar.setTitle(it)
        })

        dataModel.studyGroup.observe(ProfileFragment.newInstance(), {

        })

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

