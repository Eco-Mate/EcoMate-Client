package com.example.ecomate.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.DetailChallenge
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class DetailChallengeViewModel : ViewModel() {
    private val _challengeDetail = MutableLiveData<DetailChallenge>()
    val challengeDetail: LiveData<DetailChallenge>
        get() = _challengeDetail

    fun getDetailChallenge(challengeId: Int) {
        viewModelScope.launch {
            _challengeDetail.value = RetrofitUtil.challengeApi.getChallenge(challengeId).response
        }
    }

}