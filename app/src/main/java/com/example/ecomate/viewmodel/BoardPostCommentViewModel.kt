package com.example.ecomate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.CommentPost
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class BoardPostCommentViewModel : ViewModel() {
    fun postComment(commentPost: CommentPost) {
        viewModelScope.launch {
            RetrofitUtil.boardApi.postComment(commentPost)
        }
    }
}