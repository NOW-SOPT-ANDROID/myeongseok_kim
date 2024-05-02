package com.sopt.now.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.Profile
import com.sopt.now.data.ServicePool.infoService
import com.sopt.now.data.User
import com.sopt.now.data.datasouce.RequestLoginDto
import com.sopt.now.data.datasouce.ResponseInfoDto
import com.sopt.now.data.datasouce.ResponseLoginDto
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


    fun getInfo(userid:String) {
        infoService.getUserInfo(userid).enqueue(object : Callback<ResponseInfoDto> {
            override fun onResponse(
                call: Call<ResponseInfoDto>,
                response: Response<ResponseInfoDto>,
            ) {
                if (response.isSuccessful) {
                    val user = response.body()?.data
//                    myInfo.value = UiState.Success(response.body().data.toUser())
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

    init {
        _userData.value =
            listOf(
                Profile.frilendsProfile("배찬우", "INFP"),
                Profile.frilendsProfile("배찬우", "INFP"),
            )
    }

    fun setMyProfile(data: User) {
        _myProfile.value = data
    }

    fun updateProfileWithMyProfile() {
        _userData.value =
            listOf(
                Profile.myProfile(_myProfile.value!!.nickname, _myProfile.value!!.phonenumber),
                Profile.frilendsProfile("주효은", "INFP"),
                Profile.frilendsProfile("이유빈", "ENFP"),
                Profile.frilendsProfile("김민우", "ISTP"),
                Profile.frilendsProfile("곽의진", "CUTE"),//자기소개에서 발췌
                Profile.frilendsProfile("유정현", "ESTJ"),
            )
    }
}