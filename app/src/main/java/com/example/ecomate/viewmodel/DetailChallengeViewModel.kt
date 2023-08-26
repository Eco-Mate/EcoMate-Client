package com.example.ecomate.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.DetailChallenge
import com.example.ecomate.model.MyDetailChallenge
import com.example.ecomate.network.RetrofitUtil
import com.example.ecomate.ui.challenge.ChallengeDetailActivity
import kotlinx.coroutines.launch

class DetailChallengeViewModel : ViewModel() {
    private val _challengeDetail = MutableLiveData<DetailChallenge>()
    val challengeDetail: LiveData<DetailChallenge>
        get() = _challengeDetail

    private val _myChallengeDetail = MutableLiveData<MyDetailChallenge>()
    val myChallengeDetail: LiveData<MyDetailChallenge>
        get() = _myChallengeDetail

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getDetailChallenge(challengeId: Int) {
        viewModelScope.launch {
            _challengeDetail.value = RetrofitUtil.challengeApi.getChallenge(challengeId).response
        }
    }

    fun getDetailMyChallenge(myChallengeId: Int) {
        viewModelScope.launch {
            _myChallengeDetail.value =
                RetrofitUtil.challengeApi.getMyChallenge(myChallengeId).response
        }
    }

    fun deleteMyChallenge(
        myChallengeId: Int,
        challengeDetailActivity: ChallengeDetailActivity
    ) {
        showProgress()
        viewModelScope.launch {
            RetrofitUtil.challengeApi.deleteMyChallenge(myChallengeId)
            hideProgress()
            challengeDetailActivity.finish()
        }
    }

    fun updateActiveYn(state: Boolean, challengeId: Int) {
        viewModelScope.launch {
            RetrofitUtil.challengeApi.updateActiveYn(challengeId, state)
        }
    }

    fun tryChallenge(challengeId: Int, challengeDetailActivity: ChallengeDetailActivity) {
        showProgress()
        viewModelScope.launch {
            RetrofitUtil.challengeApi.tryChallenge(challengeId)
            challengeDetailActivity.finish()
            showProgress()
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