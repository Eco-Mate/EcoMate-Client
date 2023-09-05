package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.BoardDto
import com.example.ecomate.model.Challenge
import com.example.ecomate.model.MyDetailChallenge
import com.example.ecomate.model.StoreDto
import com.example.ecomate.network.RetrofitUtil
import com.example.ecomate.ui.community.BoardAddActivity
import com.example.ecomate.ui.map.EcostoreAddActivity
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class EcostoreAddViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    fun postEcoStore(
        createDto: StoreDto,
        file: MultipartBody.Part,
        boardAddActivity: EcostoreAddActivity
    ) {
        showProgress()
        viewModelScope.launch {
            RetrofitUtil.ecostoreApi.postEcoStore(createDto, file)
            hideProgress()
            boardAddActivity.finish()
        }
    }

    fun showProgress() {
        _isLoading.value = true
    }

    fun hideProgress() {
        _isLoading.value = false
    }
}