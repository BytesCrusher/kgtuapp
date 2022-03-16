package com.application.kgtuapp.Fragmets

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentProfileBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.NonCancellable.cancel
import java.util.*


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        val UserGroupId = 0



        binding.bChangeLanguageButton.setOnClickListener {
            context?.let {
                MaterialAlertDialogBuilder(it)
                    .setTitle(resources.getString(R.string.profile_settings_choose_language_alert_title))
                    .setMessage("Hello")//resources.getString(R.string.supporting_text))
                    /*.setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                        // Respond to neutral button press
                    }*/
                    .setNegativeButton(resources.getString(R.string.profile_settings_choose_language_alert_close)) { dialog, which ->
                        // Respond to negative button press
                    }
                    .setPositiveButton(resources.getString(R.string.profile_settings_choose_language_alert_accept)) { dialog, which ->
                        // Respond to positive button press
                    }
                    .show()
            }

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

    companion object {

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }

    //=========================

}
