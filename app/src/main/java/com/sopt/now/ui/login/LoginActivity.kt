package com.sopt.now.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.ui.main.MainActivity
import com.sopt.now.ui.signup.SignUpActivity

import com.sopt.now.util.BindingActivity
import com.sopt.now.util.getSafeParcelable
import com.sopt.now.util.toast

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
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
        Intent(this, SignUpActivity::class.java).let {
            resultLauncher.launch(it)
        }
    }

    private fun initLoginBtnClickListener() {
        if (validateLogin()) {
            val intent = Intent(this, MainActivity::class.java)
            with(intent) {
                putExtra(TAG_USER, user)
                startActivity(this)
            }
        }
    }

    private fun setResultNext() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data?.getSafeParcelable<User>(TAG_USER)
                data?.let {
                    user = it
                    binding.etLoginId.setText(user.id)
                    binding.etLoginPassword.setText(user.password)
                }
            }
        }
    }

    private fun validateLogin(): Boolean {
        return validateId() && validatePassword()
    }

    private fun validateId(): Boolean {
        require(binding.etLoginId.text.toString() == user.id) {
            toast(TOAST_NOT_EQUAL_ID)
            return false
        }
        return true
    }

    private fun validatePassword(): Boolean {
        require(binding.etLoginPassword.text.toString() == user.password) {
            toast(TOAST_NOT_EQUAL_PASSWORD)
            return false
        }
        return true
    }

    companion object {
        const val TAG_USER = "user"
        const val TOAST_NOT_EQUAL_ID = "아이디가 일치하지 않습니다."
        const val TOAST_NOT_EQUAL_PASSWORD = "비밀번호가 일치하지 않습니다."
    }
}