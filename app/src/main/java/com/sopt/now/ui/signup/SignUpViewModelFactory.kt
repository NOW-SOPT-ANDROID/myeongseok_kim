package com.sopt.now.ui.signup

import SignUpViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.data.AuthRepositoryImpl
import com.sopt.now.data.api.ServicePool
import com.sopt.now.domain.usecase.SignUpUseCase

class SignUpViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            val repository = AuthRepositoryImpl(ServicePool.authService)
            val signUpUseCase = SignUpUseCase(repository)
            return SignUpViewModel(signUpUseCase) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}