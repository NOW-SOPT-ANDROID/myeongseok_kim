package com.sopt.now.ui.signup

import SignUpViewModel
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.sopt.now.R
import com.sopt.now.data.model.User
import com.sopt.now.data.datasouce.request.RequestSignUpDto
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.ui.login.LoginActivity
import com.sopt.now.ui.login.LoginActivity.Companion.TAG_USER
import com.sopt.now.util.BindingActivity
import com.sopt.now.util.UiState
import com.sopt.now.util.toast
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        initButton()
        initObserver()
    }

    private fun initObserver() {
        viewModel.liveData.observe(this) { state ->
            when (state) {
                is UiState.Loading -> {

                }
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
        binding.btnLogin.setOnClickListener {
            signUpEvent()
        }
    }

    private fun signUpEvent() {
        viewModel.signUp(getSignUpRequestDto())
    }

    private fun navToLogin(user: User) {
        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
        intent.putExtra(TAG_USER, user)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun getSignUpRequestDto(): RequestSignUpDto {
        val id = binding.etSignupId.text.toString()
        val password = binding.etSignupPassword.text.toString()
        val nickname = binding.etSignupNickname.text.toString()
        val mbti = binding.etSignupNumbers.text.toString()
        return RequestSignUpDto(
            authenticationId = id,
            password = password,
            nickname = nickname,
            phone = mbti
        )
    }
}
