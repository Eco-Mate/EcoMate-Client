package com.example.ecomate.model

import java.io.Serializable

data class BoardBody(
    var challengeId: Int,
    var boardTitle: String,
    var boardContent: String,
) : Serializable
