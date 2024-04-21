package com.sopt.now.feature.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.feature.signup.SignUpActivity

import com.sopt.now.core.base.BindingActivity
import com.sopt.now.core.util.UiState
import com.sopt.now.core.util.getSafeParcelable
import com.sopt.now.core.util.toast
import com.sopt.now.feature.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var user: User
    private val viewModel by viewModels<LoginViewModel>()
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
        viewModel.tryLogin(
            id = binding.etLoginId.text.toString(),
            password = binding.etLoginPassword.text.toString()
        )
    }

    private fun initSignUpStateObserve() {
        viewModel.loginState.flowWithLifecycle(lifecycle).onEach { state ->
            when (state) {
                is UiState.Success -> {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    this.startActivity(intent)
                }

                is UiState.Failure -> toast(state.errorMessage)
                else -> Unit
            }
        }.launchIn(lifecycleScope)
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

    companion object {
        const val TAG_USER = "user"
    }
}