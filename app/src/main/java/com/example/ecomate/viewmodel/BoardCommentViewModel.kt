package com.example.ecomate.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Comment
import com.example.ecomate.model.DetailChallenge
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class BoardCommentViewModel : ViewModel() {
    private val _boardComment = MutableLiveData<List<Comment>>()
    val boardComment: LiveData<List<Comment>>
        get() = _boardComment

    fun getBoardComment(boardId: Int) {
        viewModelScope.launch {
            _boardComment.value = RetrofitUtil.boardApi.getComment(boardId).response["commentList"]
        }
    }

}