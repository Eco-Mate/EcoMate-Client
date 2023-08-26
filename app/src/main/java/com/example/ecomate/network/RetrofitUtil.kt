package com.example.ecomate.network

import com.example.ecomate.ApplicationClass

class RetrofitUtil {
    companion object {
        val logInApi: LogInApi = ApplicationClass.retrofit.create(LogInApi::class.java)
        val challengeApi: ChallengeApi = ApplicationClass.retrofit.create(ChallengeApi::class.java)
        val boardApi: BoardApi = ApplicationClass.retrofit.create(BoardApi::class.java)
        val chatApi: ChatApi = ApplicationClass.retrofit.create(ChatApi::class.java)
    }
}