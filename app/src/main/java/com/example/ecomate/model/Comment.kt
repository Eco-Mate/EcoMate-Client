package com.example.ecomate.model

import java.io.Serializable

data class Comment(
    val commentId: Int,
    val memberId: Int,
    val nickname: String,
    val profileImage: String,
    val content: String,
    val createdDate: String,
) : Serializable