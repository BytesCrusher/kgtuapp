package com.application.kgtuapp.Schedule

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import com.application.kgtuapp.Network.DotaApi
import com.application.kgtuapp.Schedule.QuestGoApi
import com.application.kgtuapp.Schedule.RetrofitCreateElementRequest
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val questGoApi: QuestGoApi) : ViewModel() {

    fun sendPostRequest() {
        viewModelScope.launch {
            try {
                val responseString = questGoApi.sendElementRequest(
                    RetrofitCreateElementRequest(
                        group = "19-АП(эс)"
                        /*accessToken = "",
                        questId = "",
                        pageId = "",
                        type = "1",
                        content = "Tomsk Info",
                        componentType = 0*/
                    )
                )
                Log.d("Server", "response string = ${responseString}")
            } catch (e: Exception) {
                Log.e("TAG", "Exception during request -> ${e.localizedMessage}")
            }
        }
    }
}