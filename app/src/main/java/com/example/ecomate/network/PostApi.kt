package com.example.ecomate.network

import com.example.ecomate.model.BoardList
import retrofit2.http.GET

interface PostApi {
    @GET("v1/board")
    suspend fun getAllPosts(): BoardList
}