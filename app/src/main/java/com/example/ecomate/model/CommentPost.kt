package com.example.ecomate.model

import java.io.Serializable

data class CommentPost(
    val boardId: Int,
    val content: String,
) : Serializable