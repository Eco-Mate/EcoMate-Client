package com.example.ecomate.network

import com.example.ecomate.model.LevelInfoResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LevelApi {

    @GET("v1/levels/{levelName}")
    suspend fun getLevelInfo(@Path("levelName") levelName: String): LevelInfoResponse
}