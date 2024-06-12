package com.sopt.now.domain.entity.request

import com.sopt.now.data.model.User

data class AuthRequestModel(
    val authenticationId: String,
    val password: String,
    val nickname: String,
    val phone: String,
) {
    fun toUserWithUserId(userid: String): User =
        User(
            id = authenticationId,
            password = password,
            nickname = nickname,
            phoneNumber = phone,
            userId = userid
        )
}