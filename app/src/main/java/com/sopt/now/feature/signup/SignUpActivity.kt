package com.sopt.now.feature.signup

import android.content.Intent
import android.os.Bundle
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.feature.login.LoginActivity.Companion.TAG_USER
import com.sopt.now.core.base.BindingActivity
import com.sopt.now.core.util.toast

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        initButton()
    }

    private fun initButton() {
        binding.btnLogin.setOnClickListener { if (validateSignUp()) signUpEvent() }
    }

    private fun signUpEvent() {
        val intent = Intent(this@SignUpActivity, SignUpActivity::class.java)
        intent.putExtra(
            TAG_USER, User(
                id = binding.etSignupId.text.toString(),
                password = binding.etSignupPassword.text.toString(),
                nickname = binding.etSignupNickname.text.toString(),
                mbti = binding.etSignupMBTI.text.toString()
            )
        )
        setResult(RESULT_OK, intent)
        finish()

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
        require(binding.etSignupMBTI.text.length == 4) {
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