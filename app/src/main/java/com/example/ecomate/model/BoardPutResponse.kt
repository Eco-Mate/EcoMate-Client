package com.example.ecomate.model

import java.io.Serializable

data class BoardPutResponse(
    val message: String,
    val response: Int,
) : Serializable
