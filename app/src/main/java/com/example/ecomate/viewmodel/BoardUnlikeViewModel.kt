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

class BoardUnlikeViewModel : ViewModel() {
    private val _boardUnlike = MutableLiveData<BoardLikeBody>()
    val boardUnlike: LiveData<BoardLikeBody>
        get() = _boardUnlike

    fun postBoardUnlike(boardLike: BoardLike) {
        viewModelScope.launch {
            _boardUnlike.value = RetrofitUtil.boardApi.postBoardUnlike(boardLike).response
        }
    }
}