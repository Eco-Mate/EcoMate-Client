package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.MemberBody
import com.example.ecomate.network.RetrofitUtil
import com.example.ecomate.ui.chat.ChatAddActivity
import kotlinx.coroutines.launch

class ChatAddViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _nickNameList = MutableLiveData<List<String>>()
    val nickNameList: LiveData<List<String>> get() = _nickNameList


    val memberList = mutableListOf<String>()

    init {
        postChallenge()
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

    fun postChat(activityChatAddBinding: ChatAddActivity) {
        showProgress()
        viewModelScope.launch {
            var temp = ""
            memberList.forEach {
                temp = temp + it + ", "
            }
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