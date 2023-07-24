package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Board
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class CommunityViewModel : ViewModel() {
    private val _boardList = MutableLiveData<List<Board>>()
    val boardList: LiveData<List<Board>>
        get() = _boardList

    init {
        getAllBoard()
    }

    private fun getAllBoard() {
        viewModelScope.launch {
            _boardList.value = RetrofitUtil.boardApi.getAllBoards().response["boardDtoList"]
        }
    }
}