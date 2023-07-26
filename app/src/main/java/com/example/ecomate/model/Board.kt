package com.example.ecomate.model

import java.io.Serializable

data class Board(
    val boardId: Int,
    val nickname: String,
    val challengeTitle: String,
    val boardTitle: String,
    val boardContent: String,
    val image: String,
    val likeCnt: Int,
    val createdDate : String,
) : Serializable