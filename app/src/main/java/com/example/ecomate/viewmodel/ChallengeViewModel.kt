package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.MyDetailChallenge
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class ChallengeViewModel : ViewModel() {
    private val _finishMyChallenge = MutableLiveData<List<MyDetailChallenge>>()
    val finishMyChallenge: LiveData<List<MyDetailChallenge>>
        get() = _finishMyChallenge

    init {
        getMyFinishChallenge()
    }

    fun getMyFinishChallenge() {
        viewModelScope.launch {
            _finishMyChallenge.value = RetrofitUtil.challengeApi.getMyFinishChallenge().response
        }
    }

    fun tryChallenge(challengeId: Int) {
        viewModelScope.launch {
            RetrofitUtil.challengeApi.tryChallenge(challengeId)
            _finishMyChallenge.value = RetrofitUtil.challengeApi.getMyFinishChallenge().response
            //getMyFinishChallenge()
        }
    }
}