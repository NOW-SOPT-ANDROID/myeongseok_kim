package com.sopt.now.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.ui.model.User
import com.sopt.now.domain.entity.UserEntity
import com.sopt.now.domain.usecase.LogInUseCase
import com.sopt.now.util.UiState
import kotlinx.coroutines.launch

class LoginViewModel(private val logInUseCase: LogInUseCase) : ViewModel() {
    private val _loginState = MutableLiveData<UiState<User>>()
    val loginState : LiveData<UiState<User>> = _loginState

    fun login(request: UserEntity) {
        _loginState.value = UiState.Loading
        viewModelScope.launch {
            logInUseCase(request)
                .onSuccess { response ->
                    _loginState.value =
                        UiState.Success(
                            User(
                                request.authenticationId,
                                request.password,
                                "",
                                "",
                                userId = response.toString()
                            )
                        )
                }
                .onFailure { e ->
                    _loginState.value = UiState.Error(e.message.toString())
                }
        }
    }
}

