package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.BoardLike
import com.example.ecomate.model.BoardLikeBody
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class BoardLikeViewModel : ViewModel() {
    private val _boardLike = MutableLiveData<BoardLikeBody>()
    val boardLike: LiveData<BoardLikeBody>
        get() = _boardLike

    fun postBoardLike(boardLike: BoardLike) {
        viewModelScope.launch {
            _boardLike.value = RetrofitUtil.boardApi.postBoardLike(boardLike).response
        }
    }
}