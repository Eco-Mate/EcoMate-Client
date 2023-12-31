package com.example.ecomate.model

import java.io.Serializable

data class User(
    val memberId: Int,
    val nickname: String,
    val image: String,
) : Serializable