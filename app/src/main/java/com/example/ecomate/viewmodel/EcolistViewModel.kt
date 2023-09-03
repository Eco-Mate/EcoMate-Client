package com.example.ecomate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomate.model.Board
import com.example.ecomate.model.ProfileInfo
import com.example.ecomate.model.StoreInfo
import com.example.ecomate.network.RetrofitUtil
import kotlinx.coroutines.launch

class EcolistViewModel : ViewModel() {
    private val _ecostores = MutableLiveData<List<StoreInfo>>()
    val ecostores: LiveData<List<StoreInfo>>
        get() = _ecostores

    fun getEcoStores(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _ecostores.value = RetrofitUtil.ecostoreApi.getEcoStores(latitude, longitude).response
        }
    }
}