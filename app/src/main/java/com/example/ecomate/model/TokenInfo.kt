package com.example.ecomate.model

data class TokenInfo(
    val accessToken: String,
    val memberId: Int,
    val refreshToken: String
)