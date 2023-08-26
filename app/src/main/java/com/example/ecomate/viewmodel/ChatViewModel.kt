package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.ChatInfoItem
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val _chatList = MutableLiveData<List<ChatInfoItem>>()
    val chatList: LiveData<List<ChatInfoItem>>
        get() = _chatList

    init {
        getChatList()
    }

    fun getChatList() {
        viewModelScope.launch {
            _chatList.value = RetrofitUtil.chatApi.getChatList().response
        }
    }

}