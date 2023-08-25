package com.example.ecomate.network

import com.example.ecomate.model.ChallengeDetailBody
import com.example.ecomate.model.ChallengeDto
import com.example.ecomate.model.ChallengeListBody
import com.example.ecomate.model.FinishMyChallengeCountResponse
import com.example.ecomate.model.MyChallengeListBody
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ChallengeApi {
    @GET("v1/challenges")
    suspend fun getAllChallenges(): ChallengeListBody

    @GET("v1/challenges/{challengeId}")
    suspend fun getChallenge(@Path("challengeId") challengeId: Int): ChallengeDetailBody

    @Multipart
    @POST("v1/challenges/form")
    suspend fun postChallenge(
        @Part("dto") challenge: ChallengeDto,
        @Part file: MultipartBody.Part
    )

    @GET("v1/myChallenges/member/proceeding")
    suspend fun getAllProceedingChallenge(): MyChallengeListBody

    @GET("v1/myChallenges/member/finish/cnt")
    suspend fun getFinishMyChallenge(): FinishMyChallengeCountResponse

    @PUT("v1/challenges/activeYn/{challengeId}")
    suspend fun updateActiveYn(@Path("challengeId") challengeId: Int, @Body state: Boolean)

    @DELETE("v1/challenges/{challengeId}")
    suspend fun deleteChallenge(@Path("challengeId") challengeId: Int)

}