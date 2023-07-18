package com.example.ecomate.network

import com.example.ecomate.model.Challenge
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChallengeApi {
    @GET("v1/challenges")
    suspend fun getAllChallenges(): List<Challenge>

    @GET("v1/challenges/{challengeId}")
    suspend fun getAllChallenges(@Path("challengeId") challengeId: Int): Int

}