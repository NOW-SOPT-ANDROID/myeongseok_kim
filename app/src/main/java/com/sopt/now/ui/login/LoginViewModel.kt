package com.sopt.now.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.api.ServicePool
import com.sopt.now.data.datasouce.request.RequestLoginDto
import com.sopt.now.data.datasouce.response.BaseResponse
import com.sopt.now.data.model.User
import com.sopt.now.util.UiState
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val loginService by lazy { ServicePool.authService }
    val liveData = MutableLiveData<UiState<User>>()

    fun login(request: RequestLoginDto) {
        liveData.value = UiState.Loading

        loginService.login(request).enqueue(object : Callback<BaseResponse<Unit>> {
            override fun onResponse(
                call: Call<BaseResponse<Unit>>,
                response: Response<BaseResponse<Unit>>,
            ) {
                if (response.isSuccessful) {
                    liveData.value = UiState.Success(
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
                        liveData.value = UiState.Error(errorMessage)
                    } catch (e: Exception) {
                        liveData.value = UiState.Error("로그인 실패  에러 메시지 파싱 실패")
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<Unit>>, t: Throwable) {
                liveData.value = UiState.Error("서버 에러")
            }
        })
    }
}
