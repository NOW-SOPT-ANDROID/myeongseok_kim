package com.sopt.now.domain.entity.request


data class AuthRequestModel(
    val authenticationId: String,
    val password: String,
    val nickname: String,
    val phone: String,
)