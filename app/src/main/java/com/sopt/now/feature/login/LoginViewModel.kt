package com.sopt.now.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.core.util.UiState
import com.sopt.now.domain.entity.UserEntity
import com.sopt.now.domain.usecase.GetUserInfoUseCase
import com.sopt.now.feature.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {
    private val _savedUserInfo = MutableStateFlow(
        UserEntity(
            id = "",
            password = "",
            nickname = "",
            mbti = ""
        )
    )
    val savedUserInfo: StateFlow<UserEntity> get() = _savedUserInfo

    private val _loginState = MutableSharedFlow<UiState<UserEntity>>()
    val loginState: SharedFlow<UiState<UserEntity>> get() = _loginState.asSharedFlow()

    fun tryLogin(id: String, password: String) {
        checkLoginValidate(id, password)
    }

    private fun checkLoginValidate(id: String, password: String) {
        viewModelScope.launch {
            _loginState.emit(UiState.Loading)
            val newState = when {
                !validateSavedUserInfo() -> UiState.Failure(ErrorMessage.SIGN_UP_REQUIRED)
                !validateLogin(id, password) -> UiState.Failure(ErrorMessage.ID_PASSWORD_REQUIRED)
                !validateId(id) -> UiState.Failure(ErrorMessage.INVALID_ID)
                !validatePassword(password) -> UiState.Failure(ErrorMessage.INVALID_PASSWORD)
                else -> UiState.Success(savedUserInfo.value)
            }
            _loginState.emit(newState)
        }
    }

    private fun validateSavedUserInfo() =
        savedUserInfo.value.id.isNotBlank() && savedUserInfo.value.password.isNotBlank()

    private fun validateLogin(id: String, password: String): Boolean =
        id.isNotBlank() && password.isNotBlank()

    private fun validateId(id: String): Boolean =
        id == getUserInfoUseCase.invoke().id

    private fun validatePassword(password: String): Boolean =
        password == getUserInfoUseCase.invoke().password
}