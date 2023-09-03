package com.example.ecomate.network

import com.example.ecomate.model.BoardDeleteResponse
import com.example.ecomate.model.BoardDto
import com.example.ecomate.model.BoardLikeBody
import com.example.ecomate.model.BoardLikeResponse
import com.example.ecomate.model.BoardPutBody
import com.example.ecomate.model.BoardResponse
import com.example.ecomate.model.BoardSaveBody
import com.example.ecomate.model.BoardSaveDeleteResponse
import com.example.ecomate.model.BoardSaveResponse
import com.example.ecomate.model.CommentDeleteResponse
import com.example.ecomate.model.CommentResponse
import com.example.ecomate.model.CommentPostBody
import com.example.ecomate.model.CommentPostResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface BoardApi {
    @GET("v1/boards")
    suspend fun getBoards(): BoardResponse

    @GET("v1/boards/members")
    suspend fun getMyBoards(): BoardResponse

    @GET("v1/boards/save")
    suspend fun getSaveBoards(): BoardResponse

    @GET("v1/boards/popular-posts")
    suspend fun getPopularBoards(): BoardResponse

    @Multipart
    @POST("v1/boards")
    suspend fun postBoard(
        @Part("createDto") board: BoardDto,
        @Part file: MultipartBody.Part
    )

    @PUT("v1/boards/{boardId}")
    suspend fun putBoard(
        @Path("boardId") boardId: Int,
        @Body boardPutBody: BoardPutBody
    )

    @DELETE("v1/boards/{boardId}")
    suspend fun deleteBoard(@Path("boardId") boardId: Int): BoardDeleteResponse

    @GET("v1/comments/boards/{boardId}")
    suspend fun getComment(@Path("boardId") boardId: Int): CommentResponse

    @POST("v1/comments")
    suspend fun postComment(@Body commentPostBody: CommentPostBody): CommentPostResponse

    @DELETE("v1/comments/{commentId}")
    suspend fun deleteComment(@Path("commentId") commentId: Int): CommentDeleteResponse

    @POST("v1/boards/like")
    suspend fun postBoardLike(@Body boardLikeBody: BoardLikeBody): BoardLikeResponse

    @POST("v1/boards/unlike")
    suspend fun postBoardUnlike(@Body boardLikeBody: BoardLikeBody): BoardLikeResponse

    @GET("v1/board-saves/is-saved/{boardId}")
    suspend fun getBoardSaveState(
        @Path("boardId") boardId: Int
    ): BoardSaveResponse
    @POST("v1/board-saves")
    suspend fun postBoardSave(@Body boardSaveBody: BoardSaveBody): BoardSaveResponse

    @DELETE("v1/board-saves/{boardId}")
    suspend fun deleteBoardSave(@Path("boardId") boardId: Int): BoardSaveDeleteResponse

    @GET("v1/boards/members/{reqMemberId}")
    suspend fun getUserBoards(
        @Path("reqMemberId") reqMemberId: Int
    ): BoardResponse

    @GET("v1/boards/search")
    suspend fun getSearchBoards(
        @Query("searchWord") searchWord: String
    ): BoardResponse
}