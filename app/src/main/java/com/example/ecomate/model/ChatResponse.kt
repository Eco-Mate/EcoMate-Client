package com.example.ecomate.model

data class ChatResponse(
    val message: String,
    val response: List<ChatInfoItem>
)