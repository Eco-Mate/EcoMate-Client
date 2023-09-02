package com.example.ecomate.model

data class Challenge(
    val challengeId: Int,
    val activeYn: Boolean,
    val challengeTitle: String,
    val description: String,
    val image: String,
    val goalCnt: Int,
    val treePoint: Int,
    val createdDate: String,
)