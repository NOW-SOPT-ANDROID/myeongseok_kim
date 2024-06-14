package com.sopt.now.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.data.repositoryimpl.AuthRepositoryImpl
import com.sopt.now.data.api.ServicePool
import com.sopt.now.domain.usecase.GetUserInfoUseCase
import com.sopt.now.domain.usecase.LogInUseCase
import com.sopt.now.domain.usecase.SignUpUseCase
import com.sopt.now.ui.login.LoginViewModel
import com.sopt.now.ui.main.MainViewModel
import com.sopt.now.ui.signup.SignUpViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when (modelClass) {
            SignUpViewModel::class.java -> {
                val repository = AuthRepositoryImpl(ServicePool.authService)
                val signUpUseCase = SignUpUseCase(repository)
                return SignUpViewModel(signUpUseCase) as T
            }

            LoginViewModel::class.java -> {
                val repository = AuthRepositoryImpl(ServicePool.authService)
                val loginUseCase = LogInUseCase(repository)
                return LoginViewModel(loginUseCase) as T
            }

            MainViewModel::class.java -> {
                val repository = AuthRepositoryImpl(ServicePool.authService)
                val getUserInfoUseCase = GetUserInfoUseCase(repository)
                return MainViewModel(getUserInfoUseCase) as T
            }

            else -> throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }

    }
}