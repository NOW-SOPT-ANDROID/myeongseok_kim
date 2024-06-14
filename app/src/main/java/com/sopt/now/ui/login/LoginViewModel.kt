package com.sopt.now.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.ui.model.User
import com.sopt.now.domain.entity.AuthRequestModel
import com.sopt.now.domain.usecase.LogInUseCase
import com.sopt.now.util.StringNetworkError.FAIL_ERROR
import com.sopt.now.util.StringNetworkError.LOGIN
import com.sopt.now.util.UiState
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class LoginViewModel(private val logInUseCase: LogInUseCase) : ViewModel() {
    private val _loginState = MutableLiveData<UiState<User>>()
    val loginState = _loginState

    fun login(request: AuthRequestModel) {
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

    companion object {
        private const val MESSAGE = "message"
        private const val LOCATION = "location"
    }
}

