package com.example.ecomate.model

import java.io.Serializable

data class CommentPostBody(
    val boardId: Int,
    val content: String,
) : Serializable