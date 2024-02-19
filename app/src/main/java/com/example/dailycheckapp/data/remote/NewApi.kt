package com.example.dailycheckapp.data.remote

import com.example.dailycheckapp.data.remote.dto.NewResponse
import com.example.dailycheckapp.onboarding.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewApi {
    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewResponse
}