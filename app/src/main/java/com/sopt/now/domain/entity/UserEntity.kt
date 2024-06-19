package com.sopt.now.domain.entity


data class UserEntity(
    val authenticationId: String,
    val password: String,
    val nickname: String,
    val phone: String,
)