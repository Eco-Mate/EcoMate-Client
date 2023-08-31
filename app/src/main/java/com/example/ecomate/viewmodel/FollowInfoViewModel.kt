package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.ProfileInfo
import com.example.ecomate.model.User
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class FollowInfoViewModel : ViewModel() {
    private val _followerUsers = MutableLiveData<List<User>>()
    val followerUsers: LiveData<List<User>>
        get() = _followerUsers

    private val _followingUsers = MutableLiveData<List<User>>()
    val followingUsers: LiveData<List<User>>
        get() = _followingUsers

    private val _profileInfo = MutableLiveData<ProfileInfo>()
    val profileInfo: LiveData<ProfileInfo>
        get() = _profileInfo

    fun getFollowerUsers(nickname: String) {
        viewModelScope.launch {
            _followerUsers.value = RetrofitUtil.followApi.getUserFollowers(nickname).response
        }
    }
    fun getFollowingUsers(nickname: String) {
        viewModelScope.launch {
            _followingUsers.value = RetrofitUtil.followApi.getUserFollowings(nickname).response
        }
    }

    fun getUserProfile(memberId: Int) {
        viewModelScope.launch {
            _profileInfo.value = RetrofitUtil.memberApi.getUserProfile(memberId).response
        }
    }
}