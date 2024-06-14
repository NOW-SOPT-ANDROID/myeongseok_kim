package com.sopt.now.domain.usecase

import com.sopt.now.domain.entity.AuthRequestModel
import com.sopt.now.domain.entity.BaseResponseEntity
import com.sopt.now.domain.repository.AuthRepository

class SignUpUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(request: AuthRequestModel):Result<BaseResponseEntity> =
        authRepository.signUp(request)
}