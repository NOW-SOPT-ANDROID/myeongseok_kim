package com.sopt.now.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.model.User
import com.sopt.now.domain.entity.request.AuthRequestModel
import com.sopt.now.domain.usecase.LogInUseCase
import com.sopt.now.util.UiState
import kotlinx.coroutines.launch

class LoginViewModel(private val logInUseCase: LogInUseCase) : ViewModel() {
    private val _loginState = MutableLiveData<UiState<User>>()
    val loginState = _loginState

    fun login(request: AuthRequestModel) {
        _loginState.value = UiState.Loading
        viewModelScope.launch {
            _loginState.value = logInUseCase.execute(request)
        }
    }
}

