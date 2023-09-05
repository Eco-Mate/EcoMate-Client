package com.example.ecomate.network

import com.example.ecomate.model.ChatDetailResponse
import com.example.ecomate.model.ChatMemberInfoResponse
import com.example.ecomate.model.ChatResponse
import com.example.ecomate.model.FcmToken
import com.example.ecomate.model.MemberBody
import com.example.ecomate.model.MemberResponse
import com.example.ecomate.model.RoomNameBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatApi {

    @GET("v1/chat-rooms/members")
    suspend fun getChatList(): ChatResponse

    @GET("v1/chats/rooms/{roomId}")
    suspend fun getChatDetail(@Path("roomId") roomId: Int): ChatDetailResponse

    @GET("v1/chat-rooms/search-members")
    suspend fun searchMember(@Query("nickname") nickname: String): MemberResponse

    @POST("v1/chat-rooms")
    suspend fun postChat(@Body memberBody: MemberBody)

    @PUT("v1/chat-rooms/{roomId}")
    suspend fun modifyRoomName(@Path("roomId") roomId: Int, @Body roomName: RoomNameBody)

    @PUT("v1/fcm")
    suspend fun uploadToken(@Body fcmToken: FcmToken)

    @DELETE("v1/chat-rooms/{chatRoomId}")
    suspend fun deleteChat(@Path("chatRoomId") chatRoomId: Int)

    @GET("v1/chat-rooms/{roomId}")
    suspend fun getChatMemberInfo(@Path("roomId") roomId: Int): ChatMemberInfoResponse


}