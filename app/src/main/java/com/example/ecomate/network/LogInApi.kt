package com.example.ecomate.network

import com.example.ecomate.model.LogInBody
import com.example.ecomate.model.LogInResponse
import com.example.ecomate.model.SignUpBody
import com.example.ecomate.model.SignupResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInApi {

    @POST("v1/auth/members/login")
    suspend fun login(@Body body: LogInBody): LogInResponse

    @POST("v1/auth/members/new")
    suspend fun signup(@Body body: SignUpBody): SignupResponse
}