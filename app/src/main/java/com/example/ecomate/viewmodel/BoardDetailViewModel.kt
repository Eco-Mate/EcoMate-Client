package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.BoardLikeBody
import com.example.ecomate.model.BoardLike
import com.example.ecomate.model.Comment
import com.example.ecomate.model.CommentPostBody
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class BoardDetailViewModel : ViewModel() {
    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _comments

    private val _unlike = MutableLiveData<BoardLike>()
    val unlike: LiveData<BoardLike>
        get() = _unlike

    private val _like = MutableLiveData<BoardLike>()
    val like: LiveData<BoardLike>
        get() = _like

    fun deleteBoard(boardId: Int) {
        viewModelScope.launch {
            RetrofitUtil.boardApi.deleteBoard(boardId)
        }
    }

    fun getComments(boardId: Int) {
        viewModelScope.launch {
            _comments.value = RetrofitUtil.boardApi.getComment(boardId).response["commentList"]
        }
    }

    fun postComment(boardId: Int, content: String) {
        viewModelScope.launch {
            RetrofitUtil.boardApi.postComment(CommentPostBody(boardId, content))
        }
    }

    fun deleteComment(commentId: Int) {
        viewModelScope.launch {
            RetrofitUtil.boardApi.deleteComment(commentId)
        }
    }

    fun postUnlike(boardId: Int) {
        viewModelScope.launch {
            _unlike.value = RetrofitUtil.boardApi.postBoardUnlike(BoardLikeBody(boardId)).response
        }
    }

    fun postLike(boardId: Int) {
        viewModelScope.launch {
            _like.value = RetrofitUtil.boardApi.postBoardLike(BoardLikeBody(boardId)).response
        }
    }
}