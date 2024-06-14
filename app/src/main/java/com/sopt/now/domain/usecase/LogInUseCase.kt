package com.sopt.now.domain.usecase

import com.sopt.now.domain.entity.UserEntity
import com.sopt.now.domain.repository.AuthRepository

class LogInUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(request: UserEntity):Result<Int?> =
        authRepository.login(request)
}