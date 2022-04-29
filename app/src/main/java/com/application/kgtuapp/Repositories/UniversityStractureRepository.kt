package com.application.kgtuapp.Repositories

import android.util.Log
import com.application.kgtuapp.Classes.CertainClassInScheduleDay
import com.application.kgtuapp.DataClasses.RemoteInstitute
import com.application.kgtuapp.DataClasses.RemoteStudyGroup
import com.application.kgtuapp.DataClasses.RemoteStudySubGroup
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

            //обрабатываем список институтов
            val movieArray = jsonObject.getJSONArray("structure")
            val institutesDataList = (0 until movieArray.length()).map { index -> movieArray.getJSONObject(index) }
                .map {
                        instituteJsonObject ->
                    val id = instituteJsonObject.getInt("id")
                    val name = instituteJsonObject.getString("name")

                    //обрабатываем список групп в составе института
                    val groups = instituteJsonObject.getJSONArray("groups")
                    val groupsList =  (0 until groups.length()).map { index -> groups.getJSONObject(index) }
                    .map { groupJsonObject ->
                        val id = groupJsonObject.getInt("id")
                        val name = groupJsonObject.getString("name")
                        val institute = groupJsonObject.getInt("institute")

                        //Обрабатываем список подгрупп
                        val subGroups = groupJsonObject.getJSONArray("subGroups")
                        if (subGroups.length()==0){
                            val subGroupsList = emptyList<RemoteStudySubGroup>()
                            RemoteStudyGroup(id = id, name = name, institute = institute, subGroupsList)
                        } else{
                            val subGroupsList =  (0 until subGroups.length()).map { index -> subGroups.getJSONObject(index) }
                                .map { subGroupJsonObject ->
                                    val id = subGroupJsonObject.getInt("id")
                                    val parentStudyGroupId = subGroupJsonObject.getInt("studyGroup")
                                    val numberInGroup = subGroupJsonObject.getInt("numberInGroup")
                                    val subGroupName = subGroupJsonObject.getString("name")
                                    RemoteStudySubGroup(id = id, parentStudyGroupId = parentStudyGroupId,
                                        numberInParentGroup = numberInGroup, subGroupName = subGroupName)
                                }
                            RemoteStudyGroup(id = id, name = name, institute = institute, subGroupsList)
                        }
                    }

                    RemoteInstitute(id = id, instituteName = name, groups = groupsList)
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
