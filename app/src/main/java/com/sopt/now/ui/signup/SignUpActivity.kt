package com.sopt.now.ui.signup

import SignUpViewModel
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.sopt.now.R
import com.sopt.now.ui.model.User
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.domain.entity.AuthRequestModel
import com.sopt.now.ui.ViewModelFactory
import com.sopt.now.ui.login.LoginActivity
import com.sopt.now.ui.login.LoginActivity.Companion.TAG_USER
import com.sopt.now.util.BindingActivity
import com.sopt.now.util.UiState
import com.sopt.now.util.toast

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel by viewModels<SignUpViewModel> { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        initButton()
        initObserver()
    }

    private fun initObserver() {
        viewModel.signUpState.observe(this) { state ->
            when (state) {
                is UiState.Loading -> Unit
                is UiState.Success -> {
                    toast("회원가입 성공 userid = ${state.data.userId} 입니다!")
                    navToLogin(state.data)
                }

                is UiState.Error -> {
                    toast(state.errorMessage)
                }
            }
        }
    }

    private fun initButton() {
        initSignUpBtnClickListener()
    }

    private fun initSignUpBtnClickListener() {
        binding.btnLogin.setOnClickListener {
            viewModel.signUp(getSignUpRequestDto())
        }
    }

    private fun navToLogin(user: User) {
        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
        intent.putExtra(TAG_USER, user)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun getSignUpRequestDto() = AuthRequestModel(
        authenticationId = binding.etSignupId.text.toString(),
        password = binding.etSignupPassword.text.toString(),
        nickname = binding.etSignupNickname.text.toString(),
        phone = binding.etSignupNumbers.text.toString()
    )
}
