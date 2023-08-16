package com.example.ecomate.network

import com.example.ecomate.model.BoardAddResponse
import com.example.ecomate.model.BoardLike
import com.example.ecomate.model.BoardLikeResponse
import com.example.ecomate.model.BoardResponse
import com.example.ecomate.model.CommentDeleteResponse
import com.example.ecomate.model.CommentResponse
import com.example.ecomate.model.CommentPost
import com.example.ecomate.model.CommentPostResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
interface BoardApi {
    @GET("v1/boards")
    suspend fun getAllBoards(): BoardResponse

    @GET("v1/comments/boards/{boardId}")
    suspend fun getComment(@Path("boardId") boardId: Int): CommentResponse

    @POST("v1/comments")
    suspend fun postComment(@Body commentPost: CommentPost): CommentPostResponse

    @DELETE("v1/comments/{commentId}")
    suspend fun deleteComment(@Path("commentId") commentId: Int): CommentDeleteResponse

    @Multipart
    @POST("v1/boards")
    suspend fun postBoard(
        @PartMap data: HashMap<String, RequestBody>,
        @Part file: MultipartBody.Part): BoardAddResponse

    @POST("v1/boards/like")
    suspend fun postBoardLike(@Body boardLike: BoardLike): BoardLikeResponse

    @POST("v1/boards/unlike")
    suspend fun postBoardUnlike(@Body boardLike: BoardLike): BoardLikeResponse
}