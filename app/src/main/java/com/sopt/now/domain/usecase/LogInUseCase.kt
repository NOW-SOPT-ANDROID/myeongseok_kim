package com.sopt.now.domain.usecase

import com.sopt.now.data.dto.response.BaseResponse
import com.sopt.now.domain.entity.request.AuthRequestModel
import com.sopt.now.domain.repository.AuthRepository
import retrofit2.Response

class LogInUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(request: AuthRequestModel):Result<Response<BaseResponse<Unit>>> =
        authRepository.logIn(request)
}