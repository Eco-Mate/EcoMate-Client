package com.example.ecomate.network

import com.example.ecomate.model.BoardList
import com.example.ecomate.model.CommentList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BoardApi {
    @GET("v1/boards")
    suspend fun getAllBoards(): BoardList

    @GET("v1/comments/boards/{boardId}")
    suspend fun getComment(@Path("boardId") boardId: Int): CommentList
}