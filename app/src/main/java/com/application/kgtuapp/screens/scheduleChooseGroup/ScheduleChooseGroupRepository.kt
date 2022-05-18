package com.application.kgtuapp.screens.scheduleChooseGroup

import android.util.Log
import com.application.kgtuapp.screens.scheduleChooseGroup.data.RemoteInstitute
import com.application.kgtuapp.screens.scheduleChooseGroup.data.RemoteStudyGroup
import com.application.kgtuapp.screens.scheduleChooseGroup.data.RemoteStudySubGroup
import com.application.kgtuapp.Network.Network
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class ScheduleChooseGroupRepository {

    fun searchGroupSchedule(callback: (List<RemoteInstitute>) -> Unit) {
        Thread {
            try {
                val response = Network.getData().execute()
                val responseString = response.body?.string().orEmpty()

                val institutesList: List<RemoteInstitute> = parseResponse(responseString)
                callback(institutesList)

                Log.d("Server", "response string = ${responseString}")
                Log.d("Server", "response successful = ${response.isSuccessful}")

            } catch (e: IOException) {
                //binding.tvApkVersion.text = "Произошел троллинг"
                Log.e("Server", "execute request error = ${e.message}", e)
                callback(emptyList())
            }
        }.start()
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
                }
            Log.d("Server", "parse response successful")
            return institutesDataList

        } catch (e: JSONException){
            Log.e("Server", "parse response error = ${e.message}", e)
            return emptyList()
        }

    }
}
