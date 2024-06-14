package com.sopt.now.domain.repository

import com.sopt.now.domain.entity.BaseResponseEntity
import com.sopt.now.domain.entity.UserEntity

interface AuthRepository {
    suspend fun login(authData: UserEntity): Result<Int?>
    suspend fun signUp(authData: UserEntity): Result<BaseResponseEntity>
    suspend fun getMemberInfo(userid:String): Result<UserEntity>
}