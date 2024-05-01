package com.sopt.now.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.ServicePool
import com.sopt.now.data.User
import com.sopt.now.data.datasouce.RequestLoginDto
import com.sopt.now.data.datasouce.ResponseLoginDto
import com.sopt.now.util.UiState
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val loginService by lazy { ServicePool.loginService }
    val liveData = MutableLiveData<UiState<User>>()

    fun login(request: RequestLoginDto) {
        liveData.value = UiState.Loading

        loginService.login(request).enqueue(object : Callback<ResponseLoginDto> {
            override fun onResponse(
                call: Call<ResponseLoginDto>,
                response: Response<ResponseLoginDto>,
            ) {
                if (response.isSuccessful) {
                    liveData.value = UiState.Success(User(request.authenticationId,request.password,"",""))
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

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                liveData.value = UiState.Error("서버 에러")
            }
        })
    }
}
