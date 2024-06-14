package com.sopt.now.domain.repository

import com.sopt.now.domain.entity.UserEntity
import com.sopt.now.domain.entity.BaseResponseEntity

interface AuthRepository {
    suspend fun login(authData: UserEntity): Result<Int?>
    suspend fun signUp(authData: UserEntity): Result<BaseResponseEntity>
}