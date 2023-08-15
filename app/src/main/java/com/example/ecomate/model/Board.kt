package com.example.ecomate.model

import java.io.Serializable

data class Board(
    val boardId: Int,
    val memberId: Int,
    val nickname: String,
    val profileImage: String,
    val challengeTitle: String,
    val boardTitle: String,
    val boardContent: String,
    val image: String,
    val likeCnt: Int,
    var liked: Boolean,
    val createdDate : String,
) : Serializable
