package com.sopt.now.data.dto.response

import com.sopt.now.data.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseInfoDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: UserData
){
    @Serializable
    data class UserData(
        @SerialName("authenticationId")
        val authenticationId: String,
        @SerialName("nickname")
        val nickname: String,
        @SerialName("phone")
        val phone: String,
    ) {
        fun toUser() = User(id = authenticationId, password = "", nickname = nickname, phoneNumber = phone)
    }
}

