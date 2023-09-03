package com.example.ecomate.model

import java.io.Serializable

data class MySurroundStoresResponse(
    val message: String,
    val response: List<StoreInfo>,
)
