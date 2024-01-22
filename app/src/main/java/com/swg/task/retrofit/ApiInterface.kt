package com.swg.task.retrofit

import retrofit2.http.POST
import okhttp3.RequestBody
import com.swg.task.response.ImageUploadResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.Headers

interface ApiInterface {

    @Headers("Accept: application/json")
    @POST("ATNProfileimage")
    fun doUploadImages(@HeaderMap headers: MutableMap<String, String>, @Body file: RequestBody?): Call<ImageUploadResponse?>?
}