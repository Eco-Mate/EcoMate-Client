package com.example.ecomate.model

data class BoardResponse(
    val message: String,
    val response: Map<String, List<Board>>
)