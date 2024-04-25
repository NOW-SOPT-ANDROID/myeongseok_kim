package com.sopt.now.feature.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.sopt.now.R
import com.sopt.now.feature.User
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.core.base.BindingActivity
import com.sopt.now.feature.login.LoginActivity.Companion.TAG_USER
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel by viewModels<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        initButton()
    }

    private fun initButton() {
        binding.btnLogin.setOnClickListener {
            if (viewModel.validateUser(getUserData())) {
                navigateToLoginActivity(getUserData())
            }
        }
    }

    private fun navigateToLoginActivity(userInputData: User) {
        Intent().apply {
            putExtra(TAG_USER, getUserData())
        }.also {
            setResult(RESULT_OK, it)
            finish()
        }
    }

    private fun getUserData() = User(
        id = binding.etSignupId.text.toString(),
        password = binding.etSignupPassword.text.toString(),
        nickname = binding.etSignupNickname.text.toString(),
        mbti = binding.etSignupMBTI.text.toString()
    )

}