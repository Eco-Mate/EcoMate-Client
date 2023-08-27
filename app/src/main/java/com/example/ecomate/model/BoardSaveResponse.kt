package com.example.ecomate.model

import java.io.Serializable

data class BoardSaveResponse(
    val message: String,
    val response: BoardSaveState,
) : Serializable
