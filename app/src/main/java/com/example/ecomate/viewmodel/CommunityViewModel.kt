package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Board
import com.example.ecomate.model.ProfileInfo
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class CommunityViewModel : ViewModel() {
    private val _boards = MutableLiveData<List<Board>>()
    val boards: LiveData<List<Board>>
        get() = _boards

    private val _profileInfo = MutableLiveData<ProfileInfo>()
    val profileInfo: LiveData<ProfileInfo>
        get() = _profileInfo

    init {
        getBoards()
    }

    fun getBoards() {
        viewModelScope.launch {
            _boards.value = RetrofitUtil.boardApi.getBoards().response["boardDtoList"]
        }
    }

    fun deleteBoard(boardId: Int) {
        viewModelScope.launch {
            RetrofitUtil.boardApi.deleteBoard(boardId)
        }
    }

    fun getUserProfile(memberId: Int) {
        viewModelScope.launch {
            _profileInfo.value = RetrofitUtil.memberApi.getUserProfile(memberId).response
        }
    }
}