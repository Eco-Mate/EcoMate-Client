package com.example.ecomate.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Challenge
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class BoardAddViewModel : ViewModel() {
    private val _challenges = MutableLiveData<List<Challenge>>()
    val challenges: LiveData<List<Challenge>>
        get() = _challenges

    init {
        getChallenges()
    }

    private fun getChallenges() {
        viewModelScope.launch {
            _challenges.value = RetrofitUtil.challengeApi.getAllChallenges().response
        }
    }

    fun postBoard(createDto: HashMap<String, RequestBody>, file: MultipartBody.Part) {
        viewModelScope.launch {
            RetrofitUtil.boardApi.postBoard(createDto, file)
        }
    }
}