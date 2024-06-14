package com.sopt.now.domain.entity


data class AuthRequestModel(
    val authenticationId: String,
    val password: String,
    val nickname: String,
    val phone: String,
)