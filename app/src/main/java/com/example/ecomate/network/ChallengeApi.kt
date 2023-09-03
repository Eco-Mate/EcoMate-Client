package com.example.ecomate.network

import com.example.ecomate.model.ChallengeDetailBody
import com.example.ecomate.model.ChallengeDto
import com.example.ecomate.model.ChallengeListBody
import com.example.ecomate.model.FinishMyChallengeCountResponse
import com.example.ecomate.model.MyChallengeDetailResponse
import com.example.ecomate.model.MyChallengeListResponse
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

    @GET("v1/myChallenges/member/all")
    suspend fun getMyAllChallenge(): MyChallengeListResponse

    @GET("v1/myChallenges/member/{memberId}/all")
    suspend fun getUserAllChallenge(
        @Path("memberId") memberId: Int
    ): MyChallengeListResponse

    @GET("v1/myChallenges/member/proceeding")
    suspend fun getAllProceedingChallenge(): MyChallengeListResponse

    @GET("v1/myChallenges/member/finish/cnt")
    suspend fun getFinishMyChallenge(): FinishMyChallengeCountResponse

    @PUT("v1/challenges/activeYn/{challengeId}")
    suspend fun updateActiveYn(@Path("challengeId") challengeId: Int, @Body state: Boolean)

    @DELETE("v1/challenges/{challengeId}")
    suspend fun deleteChallenge(@Path("challengeId") challengeId: Int)

    @POST("v1/myChallenges/{challengeId}")
    suspend fun tryChallenge(@Path("challengeId") challengeId: Int)

    @GET("v1/myChallenges/{myChallengeId}")
    suspend fun getMyChallenge(@Path("myChallengeId") myChallengeId: Int): MyChallengeDetailResponse

    @DELETE("v1/myChallenges/{myChallengeId}")
    suspend fun deleteMyChallenge(@Path("myChallengeId") myChallengeId: Int)

    @GET("v1/myChallenges/member/finish")
    suspend fun getMyFinishChallenge(): MyChallengeListResponse

    @PUT("v1/myChallenges/{challengeId}")
    suspend fun updateMyChallenge(@Path("challengeId") challengeId: Int)

}