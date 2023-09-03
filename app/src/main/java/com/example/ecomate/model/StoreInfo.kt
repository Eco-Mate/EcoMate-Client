package com.example.ecomate.model

import java.io.Serializable

data class StoreInfo(
    val storeId: Int,
    val storeName: String,
    val description: String,
    val image: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val likeCnt: Int,
    val liked: Boolean
)
