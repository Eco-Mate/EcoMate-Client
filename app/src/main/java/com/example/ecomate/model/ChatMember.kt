package com.example.ecomate.model

import java.io.Serializable

data class ChatMember(
    val memberId: Int,
    val image: String,
    val name: String
) : Serializable