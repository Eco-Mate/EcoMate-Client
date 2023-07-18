package com.example.ecomate.model

data class Challenge(
    val activeYn: Boolean,
    val challengeId: Int,
    val challengeTitle: String,
    val createdDate: String,
    val description: String,
    val treePoint: Int,
)