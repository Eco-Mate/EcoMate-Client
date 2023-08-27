package com.example.ecomate.model

data class SignUpBody(
    val name: String,
    val password: String,
    val nickname: String,
    val email: String,
    val statusMessage: String,
)