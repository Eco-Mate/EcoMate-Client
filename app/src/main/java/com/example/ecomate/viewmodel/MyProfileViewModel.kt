package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Board
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

    init {
        getMyProfile()
        getMyBoards()
    }

    private fun getMyProfile() {
        viewModelScope.launch {
            _ProfileInfo.value = RetrofitUtil.memberApi.getMyProfile().response
        }
    }
    private fun getMyBoards() {
        viewModelScope.launch {
            _myBoards.value = RetrofitUtil.boardApi.getMyBoards().response["boardDtoList"]
        }
    }
}