package com.example.ecomate.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Board
import com.example.ecomate.model.LikePostBody
import com.example.ecomate.model.ProfileInfo
import com.example.ecomate.model.StoreInfo
import com.example.ecomate.model.StoreLike
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class EcomapViewModel : ViewModel() {
    private val _ecostores = MutableLiveData<List<StoreInfo>>()
    val ecostores: LiveData<List<StoreInfo>>
        get() = _ecostores

    private val _like = MutableLiveData<StoreLike>()
    val like: LiveData<StoreLike>
        get() = _like

    private val _unlike = MutableLiveData<StoreLike>()
    val unlike: LiveData<StoreLike>
        get() = _unlike

    init {
        getEcoStores()
    }

    fun getEcoStores() {
        viewModelScope.launch {
            _ecostores.value = RetrofitUtil.ecostoreApi.getEcoStores().response
        }
    }

    fun getSurroundEcoStores(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _ecostores.value = RetrofitUtil.ecostoreApi.getSurroundEcoStores(latitude, longitude).response
        }
    }

    fun postLikeEcoStore(storeId: Int) {
        viewModelScope.launch {
            _like.value = RetrofitUtil.ecostoreApi.likeEcoStore(LikePostBody(storeId)).response
        }
    }

    fun postUnlikeEcoStore(storeId: Int) {
        viewModelScope.launch {
            _unlike.value = RetrofitUtil.ecostoreApi.unlikeEcoStore(LikePostBody(storeId)).response
        }
    }
}