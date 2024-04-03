package com.sopt.now.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.sopt.now.R
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.util.BindingActivity

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initButton()
    }

    private fun initButton() {
        binding.btnLogin.setOnClickListener { if (validateSignUp()) signUpEvent() }
    }

    private fun signUpEvent() {

        Intent(this@SignUpActivity, SignUpActivity::class.java).apply {
            putExtra(TAG_ID, binding.etSignupId.text.toString())
            putExtra(TAG_PASSWORD, binding.etSignupPassword.text.toString())
            putExtra(TAG_NICKNAME, binding.etSignupNickname.text.toString())
            putExtra(TAG_MBTI, binding.etSignupMBTI.text.toString())
            setResult(RESULT_OK, this)
            finish()
        }

    }

    private fun validateSignUp(): Boolean {
        if (validateID()) {
            Toast.makeText(this, VALIDATE_ID, Toast.LENGTH_SHORT).show()
            return false
        }
        if (validatePassword()) {
            Toast.makeText(this, VALIDATE_PASSWORD, Toast.LENGTH_SHORT).show()
            return false
        }
        if (validateNickName()) {
            Toast.makeText(this, VALIDATE_NICKNAME, Toast.LENGTH_SHORT).show()
            return false
        }
        if (validateMBTI()) {
            Toast.makeText(this, VALIDATE_MBTI, Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }

    private fun validateID(): Boolean {
        val text = binding.etSignupId.text.length
        return text < 6 || text > 10
    }

    private fun validatePassword(): Boolean {
        val text = binding.etSignupPassword.text.length
        return text < 8 || text > 12
    }

    private fun validateNickName(): Boolean {
        var text = binding.etSignupNickname.text
        return text.trim().isEmpty()
    }

    private fun validateMBTI(): Boolean {
        var text = binding.etSignupMBTI.text.length
        return text != 4
    }

    companion object {
        const val TAG_USER = "user"
        const val TAG_ID = "id"
        const val TAG_PASSWORD = "password"
        const val TAG_NICKNAME = "nickname"
        const val TAG_MBTI = "mbti"
        const val VALIDATE_ID = "ID입력 조건에 맞지 않습니다."
        const val VALIDATE_PASSWORD = "비밀번호 입력 조건에 맞지 않습니다."
        const val VALIDATE_NICKNAME = "닉네임 입력 조건에 맞지 않습니다."
        const val VALIDATE_MBTI = "MBTI 입력 조건에 맞지 않습니다."
    }

}