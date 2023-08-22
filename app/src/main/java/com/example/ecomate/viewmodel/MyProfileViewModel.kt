package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Board
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class MyProfileViewModel : ViewModel() {
    private val _myBoards = MutableLiveData<List<Board>>()
    val myBoards: LiveData<List<Board>>
        get() = _myBoards

    init {
        getMyBoards()
    }

    private fun getMyBoards() {
        viewModelScope.launch {
            _myBoards.value = RetrofitUtil.boardApi.getMyBoards().response["boardDtoList"]
        }
    }
}