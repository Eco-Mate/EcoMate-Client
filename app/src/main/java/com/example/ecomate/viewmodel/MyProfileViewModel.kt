package com.example.ecomate.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Board
import com.example.ecomate.model.LevelInfo
import com.example.ecomate.model.MyDetailChallenge
import com.example.ecomate.model.ProfileInfo
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception

class MyProfileViewModel : ViewModel() {
    private val _profileInfo = MutableLiveData<ProfileInfo>()
    val profileInfo: LiveData<ProfileInfo>
        get() = _profileInfo

    private val _myBoards = MutableLiveData<List<Board>>()
    val myBoards: LiveData<List<Board>>
        get() = _myBoards

    private val _myAllChallenges = MutableLiveData<List<MyDetailChallenge>>()
    val myAllChallenges: LiveData<List<MyDetailChallenge>>
        get() = _myAllChallenges

    private val _levelInfo = MutableLiveData<LevelInfo>()
    val levelInfo: LiveData<LevelInfo>
        get() = _levelInfo

    init {
        getMyProfile()
        getMyBoards()
        getMyAllChallenge()
    }

    fun getMyProfile() {
        viewModelScope.launch {
            _profileInfo.value = RetrofitUtil.memberApi.getMyProfile().response
        }
    }
    fun getMyBoards() {
        viewModelScope.launch {
            try {
                _myBoards.value = RetrofitUtil.boardApi.getMyBoards().response["boardDtoList"]
            } catch (e: Exception) {
                Log.d("ServerError",e.toString())
                _myBoards.value = mutableListOf()
            }
        }
    }

    fun getMyAllChallenge() {
        viewModelScope.launch {
            try {
                _myAllChallenges.value = RetrofitUtil.challengeApi.getMyAllChallenge().response
            } catch (e: Exception) {
                Log.d("ServerError",e.toString())
                _myAllChallenges.value = mutableListOf()
            }
        }
    }

    fun getLevelInfo(levelName: String) {
        viewModelScope.launch {
            _levelInfo.value = RetrofitUtil.levelApi.getLevelInfo(levelName).response
        }
    }
}