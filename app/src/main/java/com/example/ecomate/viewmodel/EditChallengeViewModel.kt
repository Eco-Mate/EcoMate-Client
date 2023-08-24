package com.example.ecomate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.ChallengeDto
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class EditChallengeViewModel : ViewModel() {

    fun postChallenge(challenge: ChallengeDto, file: MultipartBody.Part) {
        viewModelScope.launch {
            RetrofitUtil.challengeApi.postChallenge(challenge, file)
        }
    }
}