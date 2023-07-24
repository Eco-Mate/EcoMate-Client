package com.example.ecomate.model

data class BoardList(
    val message: String,
    val response: Map<String,List<Board>>
)