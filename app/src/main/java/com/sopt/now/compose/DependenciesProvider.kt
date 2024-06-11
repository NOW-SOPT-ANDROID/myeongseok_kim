package com.sopt.now.compose

import com.sopt.now.compose.data.AuthRepositoryImpl
import com.sopt.now.compose.data.ServicePool
import com.sopt.now.compose.domain.usecase.LogInUseCase
import com.sopt.now.compose.domain.usecase.SignUpUseCase

object DependenciesProvider {
    private val authService = ServicePool.authService
    private val authRepository : AuthRepositoryImpl = AuthRepositoryImpl(authService)
    val logInUseCase :LogInUseCase = LogInUseCase(authRepository)
    val signUpUseCase:SignUpUseCase = SignUpUseCase(authRepository)
}