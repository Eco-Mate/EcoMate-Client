package com.example.ecomate.model

import java.io.Serializable

data class BoardDeleteResponse(
    val message: String,
    val response: String,
) : Serializable
