package com.example.ecomate.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Board
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch
import java.lang.Exception

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
            try {
                _boards.value = RetrofitUtil.boardApi.getSearchBoards(searchWord).response["boardDtoList"]
            } catch (e: Exception) {
                Log.d("ServerError",e.toString())
                _boards.value = mutableListOf()
            }
        }
    }
}