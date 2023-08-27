package com.example.ecomate.model

import java.io.Serializable

data class ProfileResponse(
    val message: String,
    val response: ProfileInfo,
) : Serializable