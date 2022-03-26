package com.application.kgtuapp.Fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.application.kgtuapp.R
import com.application.kgtuapp.ViewModels.DataModel
import com.application.kgtuapp.databinding.FragmentProfileBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val dataModel: DataModel by viewModels()

    /*@RequiresApi(Build.VERSION_CODES.N)*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        /*val UserGroupId = 0*/

        /*dataModel.studyGroup.*/

        /*dataModel.studyGroup.observe(getViewLifecycleOwner(), {
            binding.tvScheduleUserStudyGroupBody.text = it
        })*/
        /*val textView = findViewB*/
        /*val tv_userGroup = this.findViewById<TextView>(R.id.tv_scheduleUserStudyGroupBody)*/
        /*tv_userGroup.text = it*/
        binding.tvScheduleUserStudyGroupBody.text = "хуету навести охота"/*dataModel.studyGroup.value*/



        binding.bProfileSettings.setOnClickListener {
            binding.tvScheduleUserStudyGroupBody.text = dataModel.studyGroup.value
            /*update()*/
            /*context?.let {
                MaterialAlertDialogBuilder(it)
                    .setTitle(resources.getString(R.string.profile_settings_choose_language_alert_title))
                    .setMessage("Hello")//resources.getString(R.string.supporting_text))
                    *//*.setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                        // Respond to neutral button press
                    }*//*
                    .setNegativeButton(resources.getString(R.string.profile_settings_choose_language_alert_close)) { dialog, which ->
                        // Respond to negative button press
                    }
                    .setPositiveButton(resources.getString(R.string.profile_settings_choose_language_alert_accept)) { dialog, which ->
                        // Respond to positive button press
                    }
                    .show()
            }*/

            /*val locale = Locale("en")
            Locale.setDefault(locale)
            val configuration = Configuration()
            configuration.locale = locale
            *//*getBaseContext().getResources().updateConfiguration(configuration, null)*//*
            getResources().updateConfiguration(configuration, null)*/

            /*al languageToLoad = "en" // your language

            val locale = Locale(languageToLoad)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale*/
            /*context?.getResources()?.updateConfiguration(
                config,
                context().getResources().getDisplayMetrics()
            )*/

            /*val res: Resources = requireContext().resources
// Change locale settings in the app.
// Change locale settings in the app.
            val dm: DisplayMetrics = res.getDisplayMetrics()
            val conf: Configuration = res.getConfiguration()
            conf.setLocale(Locale("en")) // API 17+ only.

// Use conf.locale = new Locale(...) if targeting lower versions
// Use conf.locale = new Locale(...) if targeting lower versions
            res.updateConfiguration(conf, dm)*/

        }


        /*MaterialAlertDialogBuilder(context)
            // Add customization options here
            .show()*/
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    fun update(){
        binding.tvScheduleUserStudyGroupBody.text = dataModel.studyGroup.value

        /*dataModel.studyGroup.observe(viewLifecycleOwner, {
            binding.tvScheduleUserStudyGroupBody.text = it
        })*/
    }

    companion object {

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
