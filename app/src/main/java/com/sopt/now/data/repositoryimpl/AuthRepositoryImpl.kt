package com.sopt.now.data.repositoryimpl

import com.sopt.now.data.api.AuthService
import com.sopt.now.data.dto.response.BaseResponse
import com.sopt.now.data.dto.toBaseResponseEntity
import com.sopt.now.data.dto.toRequestLoginDto
import com.sopt.now.data.dto.toRequestSignUpDto
import com.sopt.now.data.dto.toUserEntity
import com.sopt.now.data.repositoryimpl.extension.getResponseErrorMessage
import com.sopt.now.data.repositoryimpl.extension.handleThrowable
import com.sopt.now.domain.entity.ApiError
import com.sopt.now.domain.entity.UserEntity
import com.sopt.now.domain.entity.BaseResponseEntity
import com.sopt.now.domain.repository.AuthRepository
import retrofit2.Response

class AuthRepositoryImpl(private val authService: AuthService) : AuthRepository {
    override suspend fun login(authData: UserEntity): Result<Int?> =
        runCatching {
            val response: Response<BaseResponse<Unit>> =
                authService.login(authData.toRequestLoginDto())
            if (response.isSuccessful) {
                response.headers()["location"]?.toInt()
            } else {
                throw ApiError(response.getResponseErrorMessage())
            }
        }

    override suspend fun signUp(authData: UserEntity): Result<BaseResponseEntity> {
        return runCatching {
            authService.signUp(authData.toRequestSignUpDto()).toBaseResponseEntity()
        }.onFailure { throwable ->
            return throwable.handleThrowable()
        }
    }

    override suspend fun getMemberInfo(userid: String): Result<UserEntity> =
        runCatching {
            authService.getUserInfo(userid).body()?.data?.toUserEntity()
                ?: throw Exception("data is null")
        }


}