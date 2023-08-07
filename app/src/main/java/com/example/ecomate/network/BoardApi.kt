package com.example.ecomate.network

import com.example.ecomate.model.BoardAddResponse
import com.example.ecomate.model.BoardBody
import com.example.ecomate.model.BoardList
import com.example.ecomate.model.CommentList
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
interface BoardApi {
    @GET("v1/boards")
    suspend fun getAllBoards(): BoardList

    @GET("v1/comments/boards/{boardId}")
    suspend fun getComment(@Path("boardId") boardId: Int): CommentList

    @Multipart
    @POST("v1/boards")
    suspend fun postBoard(
        @PartMap data: HashMap<String, RequestBody>,
        @Part file: MultipartBody.Part): BoardAddResponse
}