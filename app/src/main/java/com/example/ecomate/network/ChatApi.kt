package com.example.ecomate.network

import com.example.ecomate.model.ChatDetailResponse
import com.example.ecomate.model.ChatResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatApi {

    @GET("v1/chat-rooms/members")
    suspend fun getChatList(): ChatResponse

    @GET("v1/chats/rooms/{roomId}")
    suspend fun getChatDetail(@Path("roomId") roomId: Int): ChatDetailResponse

}