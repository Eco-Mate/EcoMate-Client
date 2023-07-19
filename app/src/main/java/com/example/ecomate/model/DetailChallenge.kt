package com.example.ecomate.model

data class DetailChallenge(
    val activeYn: Boolean,
    val challengeId: Int,
    val challengeTitle: String,
    val createdBy: Any,
    val createdDate: String,
    val description: String,
    val goalCnt: Int,
    val lastModifiedBy: Any,
    val lastModifiedDate: String,
    val treePoint: Int
)