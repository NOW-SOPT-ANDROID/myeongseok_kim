package com.sopt.now.domain.repository

import com.sopt.now.data.dto.response.BaseResponse
import com.sopt.now.domain.entity.request.AuthRequestModel
import retrofit2.Response

interface AuthRepository {
    suspend fun logIn(authData: AuthRequestModel): Result<Response<BaseResponse<Unit>>>
    suspend fun signUp(authData: AuthRequestModel): Result<Response<BaseResponse<Unit>>>
}