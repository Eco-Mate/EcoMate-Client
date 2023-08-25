package com.example.ecomate.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Challenge
import com.example.ecomate.model.MyChallenge
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _challengeList = MutableLiveData<List<Challenge>>()
    val challengeList: LiveData<List<Challenge>>
        get() = _challengeList

    private val _finishMyChallengeCount = MutableLiveData<Int>()
    val finishMyChallengeCount: LiveData<Int>
        get() = _finishMyChallengeCount

    private val _progressMyChallengeList = MutableLiveData<List<MyChallenge>>()
    val progressMyChallengeList: LiveData<List<MyChallenge>>
        get() = _progressMyChallengeList

    init {
        getAllChallenge()
        getProgressMyChallenge()
        getFinishMyChallenge()
    }

    fun getAllChallenge() {
        viewModelScope.launch {
            _challengeList.value =
                RetrofitUtil.challengeApi.getAllChallenges().response.toMutableList()
        }
    }

    fun getFinishMyChallenge() {
        viewModelScope.launch {
            _finishMyChallengeCount.value =
                RetrofitUtil.challengeApi.getFinishMyChallenge().response
        }
    }

    fun getProgressMyChallenge() {
        viewModelScope.launch {
            _progressMyChallengeList.value =
                RetrofitUtil.challengeApi.getAllProceedingChallenge().response.toMutableList()

        }
    }
}