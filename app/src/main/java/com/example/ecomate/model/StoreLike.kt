package com.example.ecomate.model

import java.io.Serializable

data class StoreLike(
    var likeCnt: Int,
    var liked: Boolean,
) : Serializable
