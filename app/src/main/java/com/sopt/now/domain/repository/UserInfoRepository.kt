package com.sopt.now.domain.repository

import com.sopt.now.domain.entity.UserEntity

interface UserInfoRepository {
    fun saveUserInfo(user: UserEntity)
    fun getUserInfo(): UserEntity
    fun clear()
}