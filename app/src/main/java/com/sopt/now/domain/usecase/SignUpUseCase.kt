package com.sopt.now.domain.usecase

import com.sopt.now.domain.entity.UserEntity
import com.sopt.now.domain.entity.BaseResponseEntity
import com.sopt.now.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(request: UserEntity): Result<BaseResponseEntity> =
        authRepository.signUp(request)
}