package com.example.ecomate.model

import java.io.Serializable

data class FollowPostResponse(
    val message: String,
    val response: Int,
) : Serializable