package com.sopt.now.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.model.User
import com.sopt.now.domain.entity.request.AuthRequestModel
import com.sopt.now.domain.repository.AuthRepository
import com.sopt.now.util.StringNetworkError.FAIL_ERROR
import com.sopt.now.util.StringNetworkError.LOGIN
import com.sopt.now.util.UiState
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _loginState = MutableLiveData<UiState<User>>()
    val loginState = _loginState

    fun login(request: AuthRequestModel) {
        _loginState.value = UiState.Loading
        viewModelScope.launch {
            authRepository.logIn(request)
                .onSuccess { response ->
                    val userId = response.headers()["Location"].toString()
                    if (response.isSuccessful)
                        _loginState.value =
                            UiState.Success(
                                User(
                                    request.authenticationId,
                                    request.password,
                                    "",
                                    "",
                                    userId = userId
                                )
                            )
                    else {
                        val errorMessage =
                            JSONObject(response.errorBody()?.string()).getString("message")
                        _loginState.value = UiState.Error(errorMessage.toString())
                    }
                }.onFailure { e ->
                    if (e is HttpException) {
                        _loginState.value = UiState.Error(e.message())
                    } else {
                        _loginState.value = UiState.Error(FAIL_ERROR.format(LOGIN))
                    }
                }
        }
    }
}
