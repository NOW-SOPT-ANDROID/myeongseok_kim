package com.sopt.now.data.model

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.R
import com.sopt.now.compose.component.UiState
import com.sopt.now.compose.data.ServicePool
import com.sopt.now.compose.data.datasource.request.RequestLoginDto
import com.sopt.now.compose.data.datasource.request.RequestSignUpDto
import com.sopt.now.compose.data.datasource.response.ResponseLoginDto
import com.sopt.now.compose.data.datasource.response.ResponseSignUpDto
import com.sopt.now.compose.data.datasource.response.ResponseUserInfoDto
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class UserViewModel() : ViewModel() {
    private val _userData = mutableStateListOf<Profile>()
    val userData = _userData

    private val _myProfile = mutableStateOf(
        Profile(
            R.drawable.img_profile,
            "에러!",
            "에러!"
        )
    )
    val myProfile = _myProfile

    private val _myInfo = mutableStateOf(User("", "", "", ""))
    val myInfo = _myInfo

    private val authService by lazy { ServicePool.authService }

    val liveData = MutableLiveData<UiState<User>>()
    val loginData = MutableLiveData<UiState<User>>()
    val mainUserData = MutableLiveData<UiState<User>>()

    init {
        _userData.addAll(
            listOf(
                Profile(R.drawable.img_profile, "배찬우", "INFP"),
                Profile(R.drawable.img_profile, "이유빈", "ENFP"),
                Profile(R.drawable.img_profile, "김민우", "ISTP"),
                Profile(R.drawable.img_profile, "주효은", "INFP"),
                Profile(R.drawable.img_profile, "곽의진", "CUTE"),//자기소개에서 발췌
                Profile(R.drawable.img_profile, "주효은", "INFP"),
                Profile(R.drawable.img_profile, "배찬우", "INFP"),
                Profile(R.drawable.img_profile, "이유빈", "ENFP"),
                Profile(R.drawable.img_profile, "김민우", "ISTP"),
                Profile(R.drawable.img_profile, "곽의진", "CUTE"),//자기소개에서 발췌
                Profile(R.drawable.img_profile, "주효은", "INFP"),
                Profile(R.drawable.img_profile, "배찬우", "INFP"),
                Profile(R.drawable.img_profile, "이유빈", "ENFP"),
                Profile(R.drawable.img_profile, "김민우", "ISTP"),
                Profile(R.drawable.img_profile, "곽의진", "CUTE"),//자기소개에서 발췌
                Profile(R.drawable.img_profile, "주효은", "INFP"),
                Profile(R.drawable.img_profile, "배찬우", "INFP"),
                Profile(R.drawable.img_profile, "이유빈", "ENFP"),
                Profile(R.drawable.img_profile, "김민우", "ISTP"),
                Profile(R.drawable.img_profile, "곽의진", "CUTE"),//자기소개에서 발췌
            )
        )
    }

    fun signUp(request: RequestSignUpDto) {
        authService.signUp(request).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    val userId = response.headers()["Location"]
                    liveData.value = UiState.Success(request.toUserWithUserId(userId.toString()))
                } else {
                    val error = response.errorBody()?.string()
                    try {
                        val errorJson = JSONObject(error)
                        val errorMessage = errorJson.getString("message")
                        liveData.value = UiState.Error(errorMessage)
                    } catch (e: Exception) {
                        liveData.value = UiState.Error("회원가입 실패: 에러 메시지 파싱 실패")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                liveData.value = UiState.Error("서버 에러")
            }
        })
    }

    fun login(request: RequestLoginDto) {
        loginData.value = UiState.Loading

        authService.login(request).enqueue(object : Callback<ResponseLoginDto> {
            override fun onResponse(
                call: Call<ResponseLoginDto>,
                response: Response<ResponseLoginDto>,
            ) {
                if (response.isSuccessful) {
                    loginData.value = UiState.Success(
                        User(
                            request.authenticationId,
                            request.password,
                            "",
                            "",
                            userid = response.headers()["location"].toString()
                        )
                    )
                } else {
                    val error = response.errorBody()?.string()
                    try {
                        val errorJson = JSONObject(error)
                        val errorMessage = errorJson.getString("message")
                        loginData.value = UiState.Error(errorMessage)
                    } catch (e: Exception) {
                        loginData.value = UiState.Error("로그인 실패  에러 메시지 파싱 실패")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                loginData.value = UiState.Error("서버 에러")
            }
        })
    }

    fun getInfo(userid: String) {
        mainUserData.value = UiState.Loading
        viewModelScope.launch {
            runCatching {
                authService.getUserInfo(userid)
            }.onSuccess { response ->
                val user = response.body()?.data
                mainUserData.value = UiState.Success(user?.toUser() ?: User("", "", "", ""))
            }.onFailure { e ->
                if (e is HttpException) {
                    val errorMessage =
                        JSONObject(e.response()?.errorBody()?.string()).getString("message")
                    mainUserData.value = UiState.Error(errorMessage)
                } else mainUserData.value = UiState.Error("에러 ${e.message}")
            }
        }
    }

    fun setMyProfile(data: User) {
        _myInfo.value = data
        _myProfile.value = Profile(
            R.drawable.img_profile,
            data.nickname,
            data.phonenumber
        )
    }

}