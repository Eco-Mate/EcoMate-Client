package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.DetailChallenge
import com.example.ecomate.network.RetrofitUtil
import com.example.ecomate.ui.challenge.ChallengeDetailActivity
import kotlinx.coroutines.launch

class DetailChallengeViewModel : ViewModel() {
    private val _challengeDetail = MutableLiveData<DetailChallenge>()
    val challengeDetail: LiveData<DetailChallenge>
        get() = _challengeDetail

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getDetailChallenge(challengeId: Int) {
        viewModelScope.launch {
            _challengeDetail.value = RetrofitUtil.challengeApi.getChallenge(challengeId).response
        }
    }

    fun updateActiveYn(state: Boolean, challengeId: Int) {
        viewModelScope.launch {
            RetrofitUtil.challengeApi.updateActiveYn(challengeId, state)
        }
    }

    fun deleteChallenge(
        challengeId: Int,
        challengeDetailActivity: ChallengeDetailActivity
    ) {
        showProgress()
        viewModelScope.launch {
            RetrofitUtil.challengeApi.deleteChallenge(challengeId)
            hideProgress()
            challengeDetailActivity.finish()
        }
    }

    fun showProgress() {
        _isLoading.value = true
    }

    fun hideProgress() {
        _isLoading.value = false
    }
}