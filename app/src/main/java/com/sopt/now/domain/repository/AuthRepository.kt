package com.sopt.now.domain.repository

import com.sopt.now.domain.entity.AuthRequestModel
import com.sopt.now.domain.entity.BaseResponseEntity

interface AuthRepository {
    suspend fun login(authData: AuthRequestModel): Result<Int?>
    suspend fun signUp(authData: AuthRequestModel): Result<BaseResponseEntity>
}