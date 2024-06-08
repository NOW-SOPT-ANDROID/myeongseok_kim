package com.sopt.now.compose.domain

import com.sopt.now.compose.data.datasource.response.ResponseLoginDto
import com.sopt.now.compose.data.datasource.response.ResponseSignUpDto
import com.sopt.now.compose.domain.entity.AuthRequestModel
import retrofit2.Response

interface AuthRepository {
    suspend fun logIn(authData: AuthRequestModel): Result<Response<ResponseLoginDto>>
    suspend fun signUp(authData: AuthRequestModel): Result<Response<ResponseSignUpDto>>
}