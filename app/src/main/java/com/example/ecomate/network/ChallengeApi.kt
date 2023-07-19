package com.example.ecomate.network

import com.example.ecomate.model.ChallengeListBody
import com.example.ecomate.model.ChallengeDetailBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ChallengeApi {
    @GET("v1/challenges")
    suspend fun getAllChallenges(): ChallengeListBody

    @GET("v1/challenges/{challengeId}")
    suspend fun getChallenge(@Path("challengeId") challengeId: Int): ChallengeDetailBody

}