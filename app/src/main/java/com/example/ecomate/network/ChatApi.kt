package com.example.ecomate.network

import com.example.ecomate.model.ChatResponse
import retrofit2.http.GET

interface ChatApi {

    @GET("v1/chat-rooms/members")
    suspend fun getChatList(): ChatResponse
}