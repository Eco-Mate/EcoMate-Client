package com.example.ecomate.model

import java.io.Serializable

data class ChatInfoItem(
    val memberNicknameList: List<String>,
    val name: String,
    val roomId: Int
) : Serializable