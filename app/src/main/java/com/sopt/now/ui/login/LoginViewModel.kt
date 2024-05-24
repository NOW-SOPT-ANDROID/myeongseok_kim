package com.sopt.now.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.api.ServicePool
import com.sopt.now.data.datasouce.request.RequestLoginDto
import com.sopt.now.data.datasouce.response.BaseResponse
import com.sopt.now.data.model.User
import com.sopt.now.util.StringNetworkError.FAIL_ERROR
import com.sopt.now.util.StringNetworkError.LOGIN
import com.sopt.now.util.StringNetworkError.SERVER_ERROR
import com.sopt.now.util.UiState
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val loginService by lazy { ServicePool.authService }
    private val _loginState = MutableLiveData<UiState<User>>()
    val loginState = _loginState

    fun login(request: RequestLoginDto) {
        _loginState.value = UiState.Loading

        loginService.login(request).enqueue(object : Callback<BaseResponse<Unit>> {
            override fun onResponse(
                call: Call<BaseResponse<Unit>>,
                response: Response<BaseResponse<Unit>>,
            ) {
                if (response.isSuccessful) {
                    _loginState.value = UiState.Success(
                        User(
                            request.authenticationId,
                            request.password,
                            "",
                            "",
                            userId = response.headers()["location"].toString()
                        )
                    )
                } else {
                    val error = response.errorBody()?.string()
                    try {
                        val errorJson = JSONObject(error)
                        val errorMessage = errorJson.getString("message")
                        _loginState.value = UiState.Error(errorMessage)
                    } catch (e: Exception) {
                        _loginState.value = UiState.Error(FAIL_ERROR.format(LOGIN))
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<Unit>>, t: Throwable) {
                _loginState.value = UiState.Error(SERVER_ERROR)
            }
        })
    }
}
