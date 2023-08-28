package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Board
import com.example.ecomate.model.MyDetailChallenge
import com.example.ecomate.model.User
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class MyChallengesViewModel : ViewModel() {
    private val _myChallenges = MutableLiveData<List<MyDetailChallenge>>()
    val myChallenges: LiveData<List<MyDetailChallenge>>
        get() = _myChallenges

    init {
        getMyChallenges()
    }

    private fun getMyChallenges() {
        viewModelScope.launch {
            _myChallenges.value = RetrofitUtil.challengeApi.getMyAllChallenge().response
        }
    }
}