package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Board
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class MyProfileViewModel : ViewModel() {
    private val _boards = MutableLiveData<List<Board>>()
    val boards: LiveData<List<Board>>
        get() = _boards

    init {
        getBoards()
    }

    private fun getBoards() {
        viewModelScope.launch {
            _boards.value = RetrofitUtil.boardApi.getBoards().response["boardDtoList"]
        }
    }
}