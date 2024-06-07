package com.sopt.now.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.data.AuthRepositoryImpl
import com.sopt.now.data.api.ServicePool
import com.sopt.now.domain.usecase.LogInUseCase

class LoginViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val repository =
                AuthRepositoryImpl(ServicePool.authService)
            val logInUseCase = LogInUseCase(repository)
            return LoginViewModel(logInUseCase) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}