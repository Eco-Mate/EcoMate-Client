package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.MemberBody
import com.example.ecomate.model.ProfileInfo
import com.example.ecomate.network.RetrofitUtil
import com.example.ecomate.ui.chat.ChatAddActivity
import kotlinx.coroutines.launch

class ChatAddViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _nickNameList = MutableLiveData<List<String>>()
    val nickNameList: LiveData<List<String>> get() = _nickNameList

    val memberList = mutableListOf<String>()

    private val _profileInfo = MutableLiveData<ProfileInfo>()
    val profileInfo: LiveData<ProfileInfo>
        get() = _profileInfo

    init {
        postChallenge()
        getMyProfile()
    }

    fun postChallenge(
    ) {
        showProgress()
        viewModelScope.launch {
            _nickNameList.value =
                RetrofitUtil.memberApi.getAllMember().response.map { it.nickname }.toList()
            hideProgress()
        }
    }

    fun getMyProfile() {
        viewModelScope.launch {
            _profileInfo.value = RetrofitUtil.memberApi.getMyProfile().response
        }
    }

    fun postChat(activityChatAddBinding: ChatAddActivity) {
        showProgress()
        viewModelScope.launch {
            var temp = ""
            memberList.forEach {
                temp = "$temp$it, "
            }
            temp += _profileInfo.value?.nickname ?: ""
            RetrofitUtil.chatApi.postChat(MemberBody(memberList.toList(), temp))
            hideProgress()
            activityChatAddBinding.finish()
        }
    }

    fun showProgress() {
        _isLoading.value = true
    }

    fun hideProgress() {
        _isLoading.value = false
    }

}