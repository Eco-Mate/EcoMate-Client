package com.example.ecomate.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.FcmToken
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun uploadToken(token: String) {
        viewModelScope.launch {
            Log.e("MainViewModel", "MainViewModel")
            RetrofitUtil.chatApi.uploadToken(FcmToken(token))
        }
    }
}