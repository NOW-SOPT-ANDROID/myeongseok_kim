package com.sopt.now.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.api.ServicePool.infoService
import com.sopt.now.data.model.Profile
import com.sopt.now.ui.model.User
import com.sopt.now.util.StringNetworkError.FAIL_ERROR
import com.sopt.now.util.StringNetworkError.LOGIN
import com.sopt.now.util.UiState
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class MainViewModel : ViewModel() {
    private val _userData = MutableLiveData<List<Profile>>()
    val userData = _userData

    private val _myInfo = MutableLiveData<UiState<User>>()
    val myInfo = _myInfo

    private val _myProfile = MutableLiveData<User>()
    val myProfile = _myProfile

    init {
        _userData.value =
            listOf(
                Profile("배찬우", "INFP"),
                Profile("배찬우", "INFP"),
            )
    }

    fun getInfo(userid: String) {
        _myInfo.value = UiState.Loading
        viewModelScope.launch {
            runCatching {
                infoService.getUserInfo(userid)
            }.onSuccess { response ->
                if (response.isSuccessful) {
                    val user = response.body()?.data
                    _myInfo.value = UiState.Success(user?.toUser() ?: User("", "", "", ""))
                } else {
                    val errorMessage =
                        JSONObject(response.errorBody()?.string()).getString("message")
                    _myInfo.value = UiState.Error(errorMessage.toString())
                }

            }.onFailure { e ->
                if (e is HttpException) {
                    _myInfo.value = UiState.Error(e.message())
                } else {
                    _myInfo.value = UiState.Error(FAIL_ERROR.format(LOGIN))
                }
            }
        }
    }

    fun setMyProfile(data: User) {
        _myProfile.value = data
    }

    fun updateProfileWithMyProfile() {
        _userData.value =
            listOf(
                Profile(_myProfile.value!!.nickname, _myProfile.value!!.phoneNumber),
                Profile("주효은", "INFP"),
                Profile("이유빈", "ENFP"),
                Profile("김민우", "ISTP"),
                Profile("곽의진", "CUTE"),//자기소개에서 발췌
                Profile("유정현", "ESTJ"),
            )
    }
}