package com.example.ecomate.model

import java.io.Serializable

data class FollowStateResponse(
    val message: String,
    val response: Boolean,
) : Serializable