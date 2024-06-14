package com.sopt.now.domain.usecase

import com.sopt.now.domain.entity.UserEntity
import com.sopt.now.domain.repository.AuthRepository

class GetUserInfoUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(userid: String): Result<UserEntity> =
        authRepository.getMemberInfo(userid)
}