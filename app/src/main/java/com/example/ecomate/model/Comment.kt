package com.example.ecomate.model

import java.io.Serializable

data class Comment(
    val commentId: Int,
    val nickname: String,
    val commentContent: String,
    val createdDate : String,
) : Serializable