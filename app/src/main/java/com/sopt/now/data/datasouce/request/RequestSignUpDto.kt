package com.sopt.now.data.datasouce.request

import com.sopt.now.data.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDto(
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("password")
    val password: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("phone")
    val phone: String,
) {
    fun toUserWithUserId(userid: String): User =
        User(id = authenticationId, password = password, nickname = nickname, phoneNumber = phone, userId = userid)
}