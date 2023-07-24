package com.example.ecomate.network

import com.example.ecomate.model.BoardList
import retrofit2.http.GET
import retrofit2.http.Query

interface BoardApi {
    @GET("v1/board")
    suspend fun getAllBoards(): BoardList
}