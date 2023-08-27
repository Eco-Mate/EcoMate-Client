package com.example.ecomate.model

import java.io.Serializable

data class FollowResponse(
    val message: String,
    val response: List<User>,
) : Serializable