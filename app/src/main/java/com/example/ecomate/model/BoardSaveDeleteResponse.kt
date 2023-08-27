package com.example.ecomate.model

import java.io.Serializable

data class BoardSaveDeleteResponse(
    val message: String,
    val response: BoardSaveState,
) : Serializable
