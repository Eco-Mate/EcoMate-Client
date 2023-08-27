package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.ProfileInfo
import com.example.ecomate.model.MyProfileInfoBody
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class EditProfileViewModel : ViewModel() {
    private val _ProfileInfo = MutableLiveData<ProfileInfo>()
    val profileInfo: LiveData<ProfileInfo>
        get() = _ProfileInfo

    init {
        getMyProfile()
    }

    private fun getMyProfile() {
        viewModelScope.launch {
            _ProfileInfo.value = RetrofitUtil.memberApi.getMyProfile().response
        }
    }

    fun postMyProfileImage(profileImage: MultipartBody.Part) {
        viewModelScope.launch {
            RetrofitUtil.memberApi.postMyProfileImage(profileImage)
        }
    }

    fun postMyProfileInfo(
        nickname: String,
        email: String,
        statusMessage: String
    ) {
        viewModelScope.launch {
            RetrofitUtil.memberApi.postMyProfileInfo(MyProfileInfoBody(
                nickname,
                email,
                statusMessage
            ))
        }
    }

    fun deleteMyProfileImage() {
        viewModelScope.launch {
            RetrofitUtil.memberApi.deleteMyProfileImage()
        }
    }
}