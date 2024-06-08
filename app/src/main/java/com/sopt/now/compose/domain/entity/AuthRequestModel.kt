package com.sopt.now.compose.domain.entity

import com.sopt.now.compose.data.datasource.request.RequestLoginDto
import com.sopt.now.compose.data.datasource.request.RequestSignUpDto

data class AuthRequestModel(
    val authenticationId: String,
    val password: String,
    val nickname: String,
    val phone: String,
) {
    fun toRequestSignup(): RequestSignUpDto =
        RequestSignUpDto(authenticationId, password, nickname, phone)

    fun toRequestLogin(): RequestLoginDto = RequestLoginDto(authenticationId, password)
}