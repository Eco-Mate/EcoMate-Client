package com.example.ecomate.model

import java.io.Serializable

data class CommentPostResponse(
    val message: String,
    val response: Int,
) : Serializable