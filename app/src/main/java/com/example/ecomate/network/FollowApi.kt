package com.example.ecomate.network

import com.example.ecomate.model.FollowDeleteResponse
import com.example.ecomate.model.FollowPostResponse
import com.example.ecomate.model.FollowResponse
import com.example.ecomate.model.FollowStateResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
interface FollowApi {
    @GET("v1/follows/{nickname}")
    suspend fun getFollowState(
        @Path("nickname") nickname: String
    ): FollowStateResponse

    @GET("v1/follows/followings/{nickname}")
    suspend fun getFollowingUsers(
        @Path("nickname") nickname: String
    ): FollowResponse

    @GET("v1/follows/followings/{nickname}")
    suspend fun getFollowerUsers(
        @Path("nickname") nickname: String
    ): FollowResponse

    @DELETE("v1/follows/{nickname}")
    suspend fun deleteFollowState(
        @Path("nickname") nickname: String
    ): FollowDeleteResponse

    @POST("v1/follows/{nickname}")
    suspend fun postFollowState(
        @Path("nickname") nickname: String
    ): FollowPostResponse
}