package com.example.ecomate.network

import com.example.ecomate.ApplicationClass

class RetrofitUtil {
    companion object{
        val challengeApi: ChallengeApi = ApplicationClass.retrofit.create(ChallengeApi::class.java)
        val postApi: PostApi = ApplicationClass.retrofit.create(PostApi::class.java)
    }
}