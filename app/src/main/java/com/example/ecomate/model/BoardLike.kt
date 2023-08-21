package com.example.ecomate.model

import java.io.Serializable

data class BoardLike(
    var likeCnt: Int,
    var liked: Boolean,
) : Serializable
