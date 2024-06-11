package com.sopt.now.data.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.DependenciesProvider
import com.sopt.now.compose.R
import com.sopt.now.compose.component.UiState
import com.sopt.now.compose.data.ServicePool
import com.sopt.now.compose.domain.entity.AuthRequestModel
import com.sopt.now.compose.domain.usecase.LogInUseCase
import com.sopt.now.compose.domain.usecase.SignUpUseCase
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class UserViewModel(
    private val logInUseCase: LogInUseCase = DependenciesProvider.logInUseCase,
    private val signUpUseCase: SignUpUseCase = DependenciesProvider.signUpUseCase
) : ViewModel() {
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

    fun signUp(request: AuthRequestModel) {
        viewModelScope.launch {
            liveData.value = signUpUseCase.execute(request)
        }
    }

    fun login(request: AuthRequestModel) {
        viewModelScope.launch {
            loginData.value = logInUseCase.execute(request)
        }
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