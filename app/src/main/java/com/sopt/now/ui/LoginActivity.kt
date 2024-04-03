package com.sopt.now.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivityLoginBinding

import com.sopt.now.util.BindingActivity

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var id = ""
    private var password = ""
    private var mbti = ""
    private var nickname = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        setResultNext()
        initButton()
    }

    private fun initButton() {
        binding.btnLoginToSignup.setOnClickListener {
            initSignUpBtnClickListener()
        }

        binding.btnLogin.setOnClickListener {
            initLoginBtnClickListener()
        }
    }

    private fun initSignUpBtnClickListener() {
        Intent(this, SignUpActivity::class.java).apply {
            resultLauncher.launch(this)
        }
    }

    private fun initLoginBtnClickListener() {
        if (validateLogin()) {
            Intent(this, MainActivity::class.java).apply {
                putExtra(TAG_USER, User(id, password, nickname, mbti))
                startActivity(this)
            }
        }
    }

    private fun setResultNext() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                id = result.data?.getStringExtra(TAG_ID) ?: ""
                password = result.data?.getStringExtra(TAG_PASSWORD) ?: ""
                nickname = result.data?.getStringExtra(TAG_NICKNAME) ?: ""
                mbti = result.data?.getStringExtra(TAG_MBTI) ?: ""
                binding.etLoginId.setText(id)
                binding.etLoginPassword.setText(password)
            }
        }
    }

    private fun validateLogin(): Boolean {
        if (id == "" || password == "") {
            Toast.makeText(this, TOAST_NO_REGISTER, Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.etLoginId.text.toString() != id) {
            Toast.makeText(this, TOAST_NOT_EQUAL_ID, Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.etLoginPassword.text.toString() != password) {
            Toast.makeText(this, TOAST_NOT_EQUAL_PASSWORD, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    companion object {
        const val TAG_USER ="user"
        const val TAG_ID = "id"
        const val TAG_PASSWORD = "password"
        const val TAG_NICKNAME = "nickname"
        const val TAG_MBTI = "mbti"

        const val TOAST_NO_REGISTER = "회원정보가 없습니다."
        const val TOAST_NOT_EQUAL_ID = "아이디가 일치하지 않습니다."
        const val TOAST_NOT_EQUAL_PASSWORD = "비밀번호가 일치하지 않습니다."
    }
}