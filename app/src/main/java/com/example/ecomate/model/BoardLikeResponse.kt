package com.example.ecomate.model

import java.io.Serializable

data class BoardLikeResponse(
    val message: String,
    val  response: BoardLikeBody,
) : Serializable
