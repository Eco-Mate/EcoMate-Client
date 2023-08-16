package com.example.ecomate.model

data class CommentResponse(
    val message: String,
    val response: Map<String, List<Comment>>
)