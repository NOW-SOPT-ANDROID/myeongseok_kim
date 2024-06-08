package com.sopt.now.compose.data

import com.sopt.now.compose.data.datasource.response.ResponseLoginDto
import com.sopt.now.compose.data.datasource.response.ResponseSignUpDto
import com.sopt.now.compose.domain.AuthRepository
import com.sopt.now.compose.domain.entity.AuthRequestModel
import retrofit2.Response

class AuthRepositoryImpl(private val authService: AuthService) : AuthRepository {
    override suspend fun logIn(authData: AuthRequestModel): Result<Response<ResponseLoginDto>> =
        runCatching {
            authService.login(authData.toRequestLogin())
        }

    override suspend fun signUp(authData: AuthRequestModel): Result<Response<ResponseSignUpDto>> =
        runCatching {
            authService.signUp(authData.toRequestSignup())
        }
}