package com.example.ecomate.network

import com.example.ecomate.model.Challenge
import com.example.ecomate.model.ChallengeDetailBody
import com.example.ecomate.model.ChallengeListBody
import com.example.ecomate.model.MYChallengeListBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChallengeApi {
    @GET("v1/challenges")
    suspend fun getAllChallenges(): ChallengeListBody

    @GET("v1/challenges/{challengeId}")
    suspend fun getChallenge(@Path("challengeId") challengeId: Int): ChallengeDetailBody

    @POST("v1/challenges/form")
    suspend fun postChallenge(@Body challenge: Challenge)

    @GET("v1/myChallenges/user/{userId}/proceeding")
    suspend fun getAllProceedingChallenge(@Path("userId") challengeId: Int): MYChallengeListBody

}