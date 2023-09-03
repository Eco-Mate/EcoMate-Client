package com.example.ecomate.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.ApplicationClass.Companion.sharedPreferencesUtil
import com.example.ecomate.model.LogInBody
import com.example.ecomate.network.RetrofitUtil
import com.example.ecomate.ui.user.LoginActivity
import kotlinx.coroutines.launch
import java.lang.Exception

class LogInViewModel : ViewModel() {
    private val _token = MutableLiveData<Int>()
    val token: LiveData<Int>
        get() = _token

    fun logIn(name: String, password: String, activity: LoginActivity) {
        viewModelScope.launch {
            try {
                val data = RetrofitUtil.logInApi.login(LogInBody(name, password))
                sharedPreferencesUtil.setMemberId(data.response.memberId)
                sharedPreferencesUtil.setAccessToken(data.response.accessToken)
                sharedPreferencesUtil.setRefreshToken(data.response.refreshToken)
                _token.value = data.response.memberId
            } catch (e: Exception) {
                Log.d("ServerError",e.toString())
                Toast.makeText(activity, "아이디 혹은 비밀번호가 잘못되었습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}