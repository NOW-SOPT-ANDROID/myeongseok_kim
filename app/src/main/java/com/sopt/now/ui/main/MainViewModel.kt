package com.sopt.now.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.model.Profile
import com.sopt.now.data.api.ServicePool.infoService
import com.sopt.now.data.model.User
import com.sopt.now.data.datasouce.response.ResponseInfoDto
import com.sopt.now.util.UiState
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _userData = MutableLiveData<List<Profile>>()
    val userData = _userData

    val myInfo = MutableLiveData<UiState<User>>()

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
        myInfo.value = UiState.Loading
        infoService.getUserInfo(userid).enqueue(object : Callback<ResponseInfoDto> {
            override fun onResponse(
                call: Call<ResponseInfoDto>,
                response: Response<ResponseInfoDto>,
            ) {
                if (response.isSuccessful) {
                    Log.d("test_mainviewmodel", "onResponse: ${response.body()}")
                    val user = response.body()?.data

                    myInfo.value = UiState.Success(user?.toUser() ?: User("", "", "", ""))
                } else {
                    val error = response.errorBody()?.string()
                    try {
                        val errorJson = JSONObject(error)
                        val errorMessage = errorJson.getString("message")
                        myInfo.value = UiState.Error(errorMessage)
                    } catch (e: Exception) {
                        myInfo.value = UiState.Error("로그인 실패  에러 메시지 파싱 실패")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseInfoDto>, t: Throwable) {
                myInfo.value = UiState.Error("서버 에러")
            }
        })
    }

    fun setMyProfile(data: User) {
        _myProfile.value = data
    }

    fun updateProfileWithMyProfile() {
        Log.d("test", "updateProfileWithMyProfile: $_myProfile Value : ${_myProfile.value}")
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