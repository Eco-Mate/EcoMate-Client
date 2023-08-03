package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.ApplicationClass.Companion.sharedPreferencesUtil
import com.example.ecomate.model.LogInBody
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class LogInViewModel : ViewModel() {
    private val _token = MutableLiveData<Int>()
    val token: LiveData<Int>
        get() = _token

    fun logIn(name: String, password: String) {
        viewModelScope.launch {
            val data = RetrofitUtil.logInApi.login(LogInBody(name, password))
            sharedPreferencesUtil.addAccessToken(data.response.accessToken)
            sharedPreferencesUtil.addRefreshToken(data.response.refreshToken)
            _token.value = data.response.memberId
        }
    }
}