package com.sopt.now.data.datasouce

import com.sopt.now.data.User
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
    fun toUser(): User =
        User(id = authenticationId, password = password, nickname = nickname, phonenumber = phone)
}