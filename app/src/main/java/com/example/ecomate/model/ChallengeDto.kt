package com.example.ecomate.model

data class ChallengeDto(
    val activeYn: Boolean,
    val challengeTitle: String,
    val description: String,
    val goalCnt: Int,
    val treePoint: Int
)