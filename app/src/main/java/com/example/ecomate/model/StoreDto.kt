package com.example.ecomate.model

import java.io.Serializable

data class StoreDto(
    val storeName: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val address: String
)
