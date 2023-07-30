package com.example.ecomate.model

data class CommentList(
    val message: String,
    val response: Map<String, List<Comment>>
)