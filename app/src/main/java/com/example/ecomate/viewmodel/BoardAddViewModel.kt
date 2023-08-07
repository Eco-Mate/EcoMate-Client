package com.example.ecomate.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.BoardAddResponse
import com.example.ecomate.model.BoardBody
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoardAddViewModel : ViewModel() {

    fun postBoard(data: HashMap<String, RequestBody>, file: MultipartBody.Part) {
        viewModelScope.launch {
            RetrofitUtil.boardApi.postBoard(data, file)
        }
    }

}