package com.example.ecomate.model

data class StorePutBody(
    val storeName: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val address: String
)
