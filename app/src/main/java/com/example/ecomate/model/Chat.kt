package com.example.ecomate.model

import java.io.Serializable

data class Chat(
    val chatId: Int,
    val createdTime: String,
    val message: String,
    val profileImage: String?,
    val senderId: Int,
    val senderNickname: String,
    val chatType: String
) : Serializable