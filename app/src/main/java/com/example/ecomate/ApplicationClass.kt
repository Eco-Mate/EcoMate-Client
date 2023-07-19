package com.example.ecomate

import android.app.Application
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApplicationClass : Application() {

    companion object {
        const val CHALLENGE_ID = "challengeId"
        const val SERVER_URL = "http://15.164.103.242:8081/api/"
        lateinit var retrofit: Retrofit

    }

    override fun onCreate() {
        super.onCreate()
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(30, TimeUnit.SECONDS).build()
        retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()
}