package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Board
import com.example.ecomate.model.MyDetailChallenge
import com.example.ecomate.model.ProfileInfo
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class MyProfileViewModel : ViewModel() {
    private val _ProfileInfo = MutableLiveData<ProfileInfo>()
    val profileInfo: LiveData<ProfileInfo>
        get() = _ProfileInfo

    private val _myBoards = MutableLiveData<List<Board>>()
    val myBoards: LiveData<List<Board>>
        get() = _myBoards

    private val _myAllChallenges = MutableLiveData<List<MyDetailChallenge>>()
    val myAllChallenges: LiveData<List<MyDetailChallenge>>
        get() = _myAllChallenges

    init {
        getMyProfile()
        getMyBoards()
        getMyAllChallenge()
    }

    fun getMyProfile() {
        viewModelScope.launch {
            _ProfileInfo.value = RetrofitUtil.memberApi.getMyProfile().response
        }
    }
    fun getMyBoards() {
        viewModelScope.launch {
            _myBoards.value = RetrofitUtil.boardApi.getMyBoards().response["boardDtoList"]
        }
    }

    fun getMyAllChallenge() {
        viewModelScope.launch {
            _myAllChallenges.value = RetrofitUtil.challengeApi.getMyAllChallenge().response
        }
    }
}