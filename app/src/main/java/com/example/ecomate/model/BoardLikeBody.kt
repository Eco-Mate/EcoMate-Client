package com.example.ecomate.model

import java.io.Serializable

data class BoardLikeBody(
    var likeCnt: Int,
    var liked: Boolean,
) : Serializable
