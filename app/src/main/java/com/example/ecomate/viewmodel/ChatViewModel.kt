package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.ChatInfoItem
import com.example.ecomate.model.RoomNameBody
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val _chatList = MutableLiveData<List<ChatInfoItem>>()
    val chatList: LiveData<List<ChatInfoItem>>
        get() = _chatList

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading


    fun getChatList() {
        viewModelScope.launch {
            _chatList.value = RetrofitUtil.chatApi.getChatList().response
        }
    }

    fun modifyRoomName(roomId: Int, roomName: RoomNameBody) {
        showProgress()
        viewModelScope.launch {
            RetrofitUtil.chatApi.modifyRoomName(roomId, roomName)
            hideProgress()
        }
    }

    fun showProgress() {
        _isLoading.value = true
    }

    fun hideProgress() {
        _isLoading.value = false
    }

}