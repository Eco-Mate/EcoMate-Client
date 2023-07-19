package com.example.ecomate.network

import com.example.ecomate.model.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface ChallengeApi {
    @GET("v1/challenges")
    suspend fun getAllChallenges(): Body

    @GET("v1/challenges/{challengeId}")
    suspend fun getAllChallenges(@Path("challengeId") challengeId: Int): Int

}