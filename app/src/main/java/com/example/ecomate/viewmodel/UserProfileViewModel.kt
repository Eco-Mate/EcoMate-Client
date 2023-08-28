package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Board
import com.example.ecomate.model.ProfileInfo
import com.example.ecomate.model.User
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class UserProfileViewModel : ViewModel() {
    private val _userBoards = MutableLiveData<List<Board>>()
    val userBoards: LiveData<List<Board>>
        get() = _userBoards

    private val _followState = MutableLiveData<Boolean>()
    val followState: LiveData<Boolean>
        get() = _followState

    private val _profileInfo = MutableLiveData<ProfileInfo>()
    val profileInfo: LiveData<ProfileInfo>
        get() = _profileInfo

    fun getUserBoards(reqMemberId: Int) {
        viewModelScope.launch {
            _userBoards.value = RetrofitUtil.boardApi.getUserBoards(reqMemberId).response["boardDtoList"]
        }
    }

    fun getFollowState(nickname: String) {
        viewModelScope.launch {
            _followState.value = RetrofitUtil.followApi.getFollowState(nickname).response
        }
    }

    fun deleteFollowState(nickname: String) {
        viewModelScope.launch {
            RetrofitUtil.followApi.deleteFollowState(nickname)
        }
    }

    fun postFollowState(nickname: String) {
        viewModelScope.launch {
            RetrofitUtil.followApi.postFollowState(nickname)
        }
    }

    fun getUserProfile(memberId: Int) {
        viewModelScope.launch {
            _profileInfo.value = RetrofitUtil.memberApi.getUserProfile(memberId).response
        }
    }
}