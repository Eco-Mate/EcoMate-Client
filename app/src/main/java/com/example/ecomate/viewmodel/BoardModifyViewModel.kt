package com.example.ecomate.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.BoardPutBody
import com.example.ecomate.model.Challenge
import com.example.ecomate.model.MyDetailChallenge
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class BoardModifyViewModel : ViewModel() {
    private val _challenges = MutableLiveData<List<MyDetailChallenge>>()
    val challenges: LiveData<List<MyDetailChallenge>>
        get() = _challenges

    init {
        getChallenges()
    }

    private fun getChallenges() {
        viewModelScope.launch {
            _challenges.value = RetrofitUtil.challengeApi.getAllProceedingChallenge().response
        }
    }

    fun putBoard(boardId: Int, boardTitle: String, boardContent: String) {
        viewModelScope.launch {
            RetrofitUtil.boardApi.putBoard(boardId, BoardPutBody(boardTitle, boardContent))
        }
    }
}