package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.BoardDto
import com.example.ecomate.model.Challenge
import com.example.ecomate.network.RetrofitUtil
import com.example.ecomate.ui.community.BoardAddActivity
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class BoardAddViewModel : ViewModel() {
    private val _challenges = MutableLiveData<List<Challenge>>()
    val challenges: LiveData<List<Challenge>>
        get() = _challenges

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        getChallenges()
    }

    private fun getChallenges() {
        viewModelScope.launch {
            _challenges.value = RetrofitUtil.challengeApi.getAllChallenges().response
        }
    }

    fun postBoard(
        createDto: BoardDto,
        file: MultipartBody.Part,
        boardAddActivity: BoardAddActivity
    ) {
        showProgress()
        viewModelScope.launch {
            RetrofitUtil.boardApi.postBoard(createDto, file)
            hideProgress()
            boardAddActivity.finish()
        }
    }

    fun showProgress() {
        _isLoading.value = true
    }

    fun hideProgress() {
        _isLoading.value = false
    }
}