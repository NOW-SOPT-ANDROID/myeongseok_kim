package com.sopt.now.data.model
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.R
import com.sopt.now.compose.component.UiState
import com.sopt.now.compose.data.ServicePool
import com.sopt.now.compose.data.datasource.request.RequestSignUpDto
import com.sopt.now.compose.data.datasource.response.ResponseSignUpDto
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel() : ViewModel() {
    private val _userData = mutableStateListOf<Profile.FriendProfile>()
    val userData = _userData

    private val _myProfile = mutableStateOf(
        Profile.MyProfile(
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

    init {
        _userData.addAll(
            listOf(
                Profile.FriendProfile(R.drawable.img_profile, "주효은", "INFP"),
                Profile.FriendProfile(R.drawable.img_profile, "배찬우", "INFP"),
                Profile.FriendProfile(R.drawable.img_profile, "이유빈", "ENFP"),
                Profile.FriendProfile(R.drawable.img_profile, "김민우", "ISTP"),
                Profile.FriendProfile(R.drawable.img_profile, "곽의진", "CUTE"),//자기소개에서 발췌
                Profile.FriendProfile(R.drawable.img_profile, "주효은", "INFP"),
                Profile.FriendProfile(R.drawable.img_profile, "배찬우", "INFP"),
                Profile.FriendProfile(R.drawable.img_profile, "이유빈", "ENFP"),
                Profile.FriendProfile(R.drawable.img_profile, "김민우", "ISTP"),
                Profile.FriendProfile(R.drawable.img_profile, "곽의진", "CUTE"),//자기소개에서 발췌
                Profile.FriendProfile(R.drawable.img_profile, "주효은", "INFP"),
                Profile.FriendProfile(R.drawable.img_profile, "배찬우", "INFP"),
                Profile.FriendProfile(R.drawable.img_profile, "이유빈", "ENFP"),
                Profile.FriendProfile(R.drawable.img_profile, "김민우", "ISTP"),
                Profile.FriendProfile(R.drawable.img_profile, "곽의진", "CUTE"),//자기소개에서 발췌
                Profile.FriendProfile(R.drawable.img_profile, "주효은", "INFP"),
                Profile.FriendProfile(R.drawable.img_profile, "배찬우", "INFP"),
                Profile.FriendProfile(R.drawable.img_profile, "이유빈", "ENFP"),
                Profile.FriendProfile(R.drawable.img_profile, "김민우", "ISTP"),
                Profile.FriendProfile(R.drawable.img_profile, "곽의진", "CUTE"),//자기소개에서 발췌
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


    fun setMyProfile(data: User) {
        _myInfo.value = data
        _myProfile.value = Profile.MyProfile(
            R.drawable.img_profile,
            data.nickname,
            data.phonenumber
        )
    }

}