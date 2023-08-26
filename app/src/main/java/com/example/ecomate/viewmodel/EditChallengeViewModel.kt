package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.ChallengeDto
import com.example.ecomate.network.RetrofitUtil
import com.example.ecomate.ui.challenge.EditChallengeActivity
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class EditChallengeViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun postChallenge(
        challenge: ChallengeDto,
        file: MultipartBody.Part,
        editChallengeActivity: EditChallengeActivity
    ) {
        showProgress()
        viewModelScope.launch {
            RetrofitUtil.challengeApi.postChallenge(challenge, file)
            hideProgress()
            editChallengeActivity.finish()
        }
    }

    fun showProgress() {
        _isLoading.value = true
    }

    fun hideProgress() {
        _isLoading.value = false
    }
}