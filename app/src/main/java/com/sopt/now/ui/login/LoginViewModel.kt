package com.sopt.now.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.api.ServicePool
import com.sopt.now.data.dto.request.RequestLoginDto
import com.sopt.now.data.model.User
import com.sopt.now.util.StringNetworkError.FAIL_ERROR
import com.sopt.now.util.StringNetworkError.LOGIN
import com.sopt.now.util.UiState
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class LoginViewModel : ViewModel() {
    private val loginService by lazy { ServicePool.authService }
    private val _loginState = MutableLiveData<UiState<User>>()
    val loginState = _loginState

    fun login(request: RequestLoginDto) {
        _loginState.value = UiState.Loading
        viewModelScope.launch {
            runCatching {
                loginService.login(request)
            }.onSuccess { response ->
                val userId = response.headers()["Location"].toString()
                if (response.isSuccessful)
                    _loginState.value =
                        UiState.Success( User(
                            request.authenticationId,
                            request.password,
                            "",
                            "",
                            userId = userId
                        ))
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
