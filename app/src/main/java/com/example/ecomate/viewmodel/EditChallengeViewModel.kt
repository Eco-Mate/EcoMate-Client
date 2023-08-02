package com.example.ecomate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Challenge
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class EditChallengeViewModel : ViewModel() {

    fun postChallenge(challenge: Challenge) {
        viewModelScope.launch {
            RetrofitUtil.challengeApi.postChallenge(challenge)
        }
    }
}