package com.sopt.now.data

import com.sopt.now.data.api.AuthService
import com.sopt.now.data.dto.response.BaseResponse
import com.sopt.now.domain.entity.request.AuthRequestModel
import com.sopt.now.domain.repository.AuthRepository
import retrofit2.Response

class AuthRepositoryImpl(private val authService: AuthService) : AuthRepository {
    override suspend fun logIn(authData: AuthRequestModel): Result<Response<BaseResponse<Unit>>> =
        runCatching {
            authService.login(authData.toRequestLogin())
        }

    override suspend fun signUp(authData: AuthRequestModel): Result<Response<BaseResponse<Unit>>> =
        runCatching {
            authService.signUp(authData.toRequestSignup())
        }
}