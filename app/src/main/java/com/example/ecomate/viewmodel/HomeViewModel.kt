package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Board
import com.example.ecomate.model.Challenge
import com.example.ecomate.model.MyDetailChallenge
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _challengeList = MutableLiveData<List<Challenge>>()
    val challengeList: LiveData<List<Challenge>>
        get() = _challengeList

    private val _finishMyChallengeCount = MutableLiveData<Int>()
    val finishMyChallengeCount: LiveData<Int>
        get() = _finishMyChallengeCount

    private val _progressMyChallengeList = MutableLiveData<List<MyDetailChallenge>>()
    val progressMyChallengeList: LiveData<List<MyDetailChallenge>>
        get() = _progressMyChallengeList

    private val _popularBoards = MutableLiveData<List<Board>>()
    val popularBoards: LiveData<List<Board>>
        get() = _popularBoards

    init {
        getAllChallenge()
        getProgressMyChallenge()
        getFinishMyChallenge()
        getPopularBoards()
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

    fun getPopularBoards() {
        viewModelScope.launch {
            _popularBoards.value = RetrofitUtil.boardApi.getPopularBoards().response["boardDtoList"]
        }
    }
}