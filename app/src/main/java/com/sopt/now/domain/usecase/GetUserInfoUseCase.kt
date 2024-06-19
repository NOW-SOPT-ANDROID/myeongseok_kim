package com.sopt.now.domain.usecase

import com.sopt.now.domain.entity.UserEntity
import com.sopt.now.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userid: String): Result<UserEntity> =
        authRepository.getMemberInfo(userid)
}