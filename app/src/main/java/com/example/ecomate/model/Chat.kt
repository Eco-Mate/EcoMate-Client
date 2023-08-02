package com.example.ecomate.model

import java.io.Serializable

data class Chat(
    val chatId: Int,
    val image: String,
    val name: String,
    val members: List<String>,
) : Serializable