package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Board
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class BoardSearchViewModel : ViewModel() {
    private val _boards = MutableLiveData<List<Board>>()
    val boards: LiveData<List<Board>>
        get() = _boards

    init {
        getBoards()
    }

    fun getBoards() {
        viewModelScope.launch {
            _boards.value = RetrofitUtil.boardApi.getBoards().response["boardDtoList"]
        }
    }

    fun getSearchBoards(searchWord: String) {
        viewModelScope.launch {
            _boards.value = RetrofitUtil.boardApi.getSearchBoards(searchWord).response["boardDtoList"]
        }
    }
}