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

class SavedEcostoresViewModel : ViewModel() {
    private val _ecostores = MutableLiveData<List<StoreInfo>>()
    val ecostores: LiveData<List<StoreInfo>>
        get() = _ecostores

    init {
        getMyLikeEcoStores()
    }

    fun getMyLikeEcoStores() {
        viewModelScope.launch {
            _ecostores.value = RetrofitUtil.ecostoreApi.getMyLikeEcoStores().response
        }
    }
}