package com.example.ecomate.model

data class TokenInfo(
    val memberId: Int,
    val accessToken: String,
    val refreshToken: String
)