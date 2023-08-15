package com.example.ecomate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class BoardAddViewModel : ViewModel() {

    fun postBoard(data: HashMap<String, RequestBody>, file: MultipartBody.Part) {
        viewModelScope.launch {
            RetrofitUtil.boardApi.postBoard(data, file)
        }
    }

}