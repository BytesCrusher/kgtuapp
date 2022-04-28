package com.application.kgtuapp.Fragments

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.kgtuapp.BuildConfig
import com.application.kgtuapp.Network.Network
import com.application.kgtuapp.R
import com.application.kgtuapp.databinding.FragmentNotificationsBinding
import com.application.kgtuapp.databinding.FragmentScheduleBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import okhttp3.*
import org.json.JSONException
import java.io.IOException


class NotificationsFragment : Fragment() {
    private lateinit var binding: FragmentNotificationsBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(layoutInflater, container, false)
        binding.ibToolbarGoBack.setOnClickListener {
            changeContentFragmentByNotificationsFragment(
                R.id.l_mainActivityFragment,
                ScheduleFragment.newInstance()
            )
        }

        binding.tvApkVersion.text = """
            Вариант сборки = ${BuildConfig.BUILD_TYPE}            
            Версия приложения = ${BuildConfig.VERSION_NAME}
            Код версии приложения = ${BuildConfig.VERSION_CODE}
        """//.trimIndent()

        loadRandomFact()
        return binding.root
    }

    private fun loadRandomFact() {
        /*runOnUiThread {
            progressBar.visibility = View.VISIBLE
        }*/


        //клиент и его методы хранятся в синглтон-объекте Network


        /*okHttpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Мы в дерьме")
            }

            override fun onResponse(call: Call, response: Response) {
                println("Еще живем")
                json = response.body.toString()
                    //?.body()?.string()
                //txt = (JSONObject(json)?.getJSONObject("value").get("joke")).toString()
                //binding.tvApkVersion.text = Html.fromHtml(txt)
            }
        })*/
        binding.tvApkVersion.text = "json"

        Thread {
            try {
                val response = Network.getData().execute()

                val responseString = response.body?.string().orEmpty()

                val dataList = parseResponse(responseString)

                dataList.forEach {
                    binding.tvApkVersion.text = "${binding.tvApkVersion.text} + $it"
                }

                Log.d("Server", "response string = ${responseString}")
                Log.d("Server", "response successful = ${response.isSuccessful}")
                println("Еще живем")

            } catch (e: IOException){
                binding.tvApkVersion.text = "Произошел троллинг"
                Log.e("Server", "execute request error = ${e.message}", e)
                println("Мы в дерьме")
            }

            //Thread.sleep(1000)

            //callback(emptyList())
        }.start()

    }
    //{
    //   "type":"success",
    //   "value":{
    //      "id":380,
    //      "joke":"Chuck Norris does not follow fashion trends, they follow him. But then he turns around and kicks their ass. Nobody follows Chuck Norris.",
    //      "categories":[
    //      ]
    //   }
    //}

    private fun parseResponse(responseBodyString: String): MutableList<String>{
        try {
            val jsonObject = JSONObject(responseBodyString)

            val list = mutableListOf<String>()
            val type = jsonObject.getString("type")
            list.add(type)

            //получить список JSON объектов
            //это из другой оперы
            /*val movieArray = jsonObject.getJSONArray("Search")
            val movies = (0 until movieArray.length()).map { index -> movieArray.getJSONObject(index) }
                .map {
                    movieJsonObject ->
                    val title = movieJsonObject.getString("Title")
                    val year = movieJsonObject.getString("Year")
                    val id = movieJsonObject.getString("imdbID")

                    RemoteMovie(id = id, title = title, year = year)
                }
            return movies*/

            return list

        } catch (e: JSONException){
            val list = mutableListOf<String>()
            Log.e("Server", "parse response error = ${e.message}", e)
            return list
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = NotificationsFragment()
    }

    private fun changeContentFragmentByNotificationsFragment(idContainer: Int, newFragment:Fragment){
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(idContainer, newFragment)
            .commit()
    }
}