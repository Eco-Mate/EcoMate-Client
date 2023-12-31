package com.example.ecomate.model

data class DetailChallenge(
    val activeYn: Boolean,
    val challengeId: Int,
    val challengeTitle: String,
    val createdDate: String,
    val description: String,
    val goalCnt: Int,
    val image: String,
    val treePoint: Int
)