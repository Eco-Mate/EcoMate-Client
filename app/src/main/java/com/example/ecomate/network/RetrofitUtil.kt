package com.example.ecomate.network

import com.example.ecomate.ApplicationClass

class RetrofitUtil {
    companion object{
        val challengeApi: ChallengeApi = ApplicationClass.retrofit.create(ChallengeApi::class.java)
    }
}