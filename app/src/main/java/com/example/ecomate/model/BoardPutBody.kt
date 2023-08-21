package com.example.ecomate.model

import java.io.Serializable

data class BoardPutBody(
    val boardTitle: String,
    val boardContent: String,
) : Serializable
