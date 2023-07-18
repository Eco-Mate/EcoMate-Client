package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Challenge
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class ChallengeViewModel : ViewModel() {
    private val _challengeList = MutableLiveData<List<Challenge>>()
    val challengeList: LiveData<List<Challenge>>
        get() = _challengeList

    init {
        getAllChallenge()
    }

    private fun getAllChallenge() {
        viewModelScope.launch {
            _challengeList.value = RetrofitUtil.challengeApi.getAllChallenges()
        }
    }
}