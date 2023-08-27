package com.example.ecomate.model

import java.io.Serializable

data class ProfileInfo(
    val memberId: Int,
    val role: String,
    val level: String,
    val totalTreePoint: Int,
    val profileImage: String,
    val nickname: String,
    val name: String,
    val email: String,
    val statusMessage: String,
    val followerCnt: Int,
    val followingCnt: Int,
) : Serializable