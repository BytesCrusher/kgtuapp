package com.application.kgtuapp.Repositories

import android.util.Log
import com.application.kgtuapp.Classes.CertainClassInScheduleDay
import com.application.kgtuapp.DataClasses.RemoteInstitute
import com.application.kgtuapp.Network.Network
import okhttp3.HttpUrl
import okhttp3.Request
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class UniversityStractureRepository {

    fun searchGroupSchedule(callback: (List<RemoteInstitute>) -> Unit) {
        Thread {
            try {
                val response = Network.getData().execute()
                val responseString = response.body?.string().orEmpty()

                //data = responseString

                //binding.tvApkVersion.text = data
                val institutesList: List<RemoteInstitute> = parseResponse(responseString)
                callback(institutesList)


                /*dataList.forEach {
                    binding.tvApkVersion.text = "${binding.tvApkVersion.text} + $it"
                }*/

                Log.d("Server", "response string = ${responseString}")
                Log.d("Server", "response successful = ${response.isSuccessful}")
                println("Еще живем")

            } catch (e: IOException) {
                //binding.tvApkVersion.text = "Произошел троллинг"
                Log.e("Server", "execute request error = ${e.message}", e)
                println("Мы в дерьме")
                callback(emptyList())
            }

            //Thread.sleep(1000)

            //callback(emptyList())
        }.start()

        //binding.tvApkVersion.text = data
    }

    private fun parseResponse(responseBodyString: String): List<RemoteInstitute> {
        try {
            val jsonObject = JSONObject(responseBodyString)

            val movieArray = jsonObject.getJSONArray("structure")
            val institutesDataList = (0 until movieArray.length()).map { index -> movieArray.getJSONObject(index) }
                .map {
                        movieJsonObject ->
                    val id = movieJsonObject.getInt("id")
                    val name = movieJsonObject.getString("name")

                    println("there $id")
                    RemoteInstitute(id = id, instituteName = name)
                    //RemoteMovie(id = id, title = title, year = year)
                }
            Log.d("Server", "parse response successful")
            return institutesDataList

            //val list = mutableListOf<String>()

            //val type = jsonObject.getString("type")
            //list.add(type)

            //val movieArray = jsonObject.toJSONArray()
            /*val institutesDataList = mutableListOf<String>()

            for (i in 0 until jsonObject.length()){
                jsonObject.getJSONArray()
            }*/

            //Log.d("Server", "jsonObject.length = ${jsonObject.length()}")
            //println(jsonObject.length())


            //.map {   }
            /*.map {
                    movieJsonObject ->
                val title = movieJsonObject.getString("Title")
                val year = movieJsonObject.getString("Year")
                val id = movieJsonObject.getString("imdbID")

                //RemoteMovie(id = id, title = title, year = year)
            }*/
            //return movies

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

            //return list
            //return institutesDataList
            //return emptyList()

        } catch (e: JSONException){
            //val list = mutableListOf<String>()
            Log.e("Server", "parse response error = ${e.message}", e)
            return emptyList()
        }

    }
}
