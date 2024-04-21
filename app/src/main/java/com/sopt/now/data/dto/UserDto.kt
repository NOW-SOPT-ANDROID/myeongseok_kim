package com.sopt.now.data.dto

import com.sopt.now.domain.entity.UserEntity

data class UserDto(
    val id: String,
    val password: String,
    val nickname: String,
    val mbti: String
)
{
    fun toUserEntity() = UserEntity(
        id = id,
        password = password,
        nickname = nickname,
        mbti = mbti
    )
}
