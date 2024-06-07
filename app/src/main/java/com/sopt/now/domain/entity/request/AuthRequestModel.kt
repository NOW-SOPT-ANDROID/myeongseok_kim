package com.sopt.now.domain.entity.request

import com.sopt.now.data.dto.request.RequestLoginDto
import com.sopt.now.data.dto.request.RequestSignUpDto
import com.sopt.now.data.model.User

data class AuthRequestModel(
    val authenticationId: String,
    val password: String,
    val nickname: String,
    val phone: String,
) {
    fun toRequestSignup(): RequestSignUpDto =
        RequestSignUpDto(authenticationId, password, nickname, phone)

    fun toRequestLogin(): RequestLoginDto = RequestLoginDto(authenticationId, password)
    fun toUserWithUserId(userid: String): User =
        User(
            id = authenticationId,
            password = password,
            nickname = nickname,
            phoneNumber = phone,
            userId = userid
        )
}