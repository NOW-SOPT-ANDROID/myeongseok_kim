package com.sopt.now.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.data.datasouce.RequestLoginDto
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.ui.main.MainActivity
import com.sopt.now.ui.signup.SignUpActivity
import com.sopt.now.util.BindingActivity
import com.sopt.now.util.UiState
import com.sopt.now.util.toast

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        initButton()
        initObserver()
    }

    private fun initButton() {
        binding.btnLoginToSignup.setOnClickListener {
            initSignUpBtnClickListener()
        }

        binding.btnLogin.setOnClickListener {
            loginButtonEvent()
        }
    }

    private fun initObserver() {
        viewModel.liveData.observe(this) { state ->
            when (state) {
                is UiState.Loading -> {

                }

                is UiState.Success -> {
                    navToHome(state.data)
                }

                is UiState.Error -> {
                    toast(state.errorMessage)
                }
            }
        }
    }

    private fun navToHome(user: User) {
        val intent = Intent(this, MainActivity::class.java)
        with(intent) {
            putExtra(TAG_USER, user)
            startActivity(this)
        }
    }

    private fun initSignUpBtnClickListener() {
        Intent(this, SignUpActivity::class.java).let {
            resultLauncher.launch(it)
        }
    }

    private fun loginButtonEvent() {
        viewModel.login(getLoginRequestDto())
    }

    private fun getLoginRequestDto() =
        RequestLoginDto(
            authenticationId = binding.etLoginId.text.toString(),
            password = binding.etLoginPassword.text.toString()
        )

    companion object {
        const val TAG_USER = "user"
    }
}