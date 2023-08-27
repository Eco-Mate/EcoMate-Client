package com.example.ecomate.model

import java.io.Serializable

data class MyProfileInfoBody(
    val nickname: String,
    val email: String,
    val statusMessage: String,
) : Serializable