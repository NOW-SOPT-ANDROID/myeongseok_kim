package com.sopt.now.ui.signup

import android.content.Intent
import android.os.Bundle
import com.sopt.now.R
import com.sopt.now.data.ServicePool.authService
import com.sopt.now.data.User
import com.sopt.now.data.datasouce.RequestSignUpDto
import com.sopt.now.data.datasouce.ResponseSignUpDto
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.ui.login.LoginActivity.Companion.TAG_USER
import com.sopt.now.util.BindingActivity
import com.sopt.now.util.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        initButton()
    }

    private fun initButton() {
        binding.btnLogin.setOnClickListener {  signUp() }
    }

    private fun navToLogin() {
        val intent = Intent(this@SignUpActivity, SignUpActivity::class.java)
        intent.putExtra(
            TAG_USER, User(
                id = binding.etSignupId.text.toString(),
                password = binding.etSignupPassword.text.toString(),
                nickname = binding.etSignupNickname.text.toString(),
                mbti = binding.etSignupNumbers.text.toString()
            )
        )
        setResult(RESULT_OK, intent)
        finish()

    }

    private fun signUp() {
        val signUpRequest = getSignUpRequestDto()
        authService.signUp(signUpRequest).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
//                    val data: ResponseSignUpDto? = response.body()
                    val userId = response.headers()["location"]
                    toast("회원가입 성공 유저의 ID는 $userId 입니둥")
                    navToLogin()
                } else {
                    val error = response.message()
                    toast("로그인이 실패 $error")
                }
            }
            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                toast("서버 에러 발생 ")
            }
        })
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        val id = binding.etSignupId.text.toString()
        val password = binding.etSignupPassword.text.toString()
        val nickname = binding.etSignupNickname.text.toString()
        val phoneNumber = binding.etSignupNumbers.text.toString()
        return RequestSignUpDto(
            authenticationId = id,
            password = password,
            nickname = nickname,
            phone = phoneNumber
        )
    }

    private fun validateSignUp() =
        validateID() && validatePassword() && validateNickName() && validateMBTI()

    private fun validateID(): Boolean {
        require(binding.etSignupId.text.length in 6..10) {
            toast(VALIDATE_ID)
            return false
        }
        return true
    }

    private fun validatePassword(): Boolean {
        require(binding.etSignupPassword.text.length in 8..12) {
            toast(VALIDATE_PASSWORD)
            return false
        }
        return true
    }

    private fun validateNickName(): Boolean {
        require(binding.etSignupNickname.text.isNotBlank()) {
            toast(VALIDATE_NICKNAME)
            return false
        }
        return true
    }

    private fun validateMBTI(): Boolean {
        require(binding.etSignupNumbers.text.length == 4) {
            toast(VALIDATE_MBTI)
            return false
        }
        return true
    }

    companion object {
        const val VALIDATE_ID = "ID입력 조건에 맞지 않습니다."
        const val VALIDATE_PASSWORD = "비밀번호 입력 조건에 맞지 않습니다."
        const val VALIDATE_NICKNAME = "닉네임 입력 조건에 맞지 않습니다."
        const val VALIDATE_MBTI = "MBTI 입력 조건에 맞지 않습니다."
    }

}