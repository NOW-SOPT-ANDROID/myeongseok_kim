package com.sopt.now.feature.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.R
import com.sopt.now.feature.User
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
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        initRegisterResultLauncher()
        Log.d("testmain", "initLayout: initRegi done")
        initButton()
        Log.d("testmain", "initLayout: initBtn done")
//        initSignUpStateObserve()//error
        Log.d("testmain", "initLayout: stateObserve done")
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



    private fun initRegisterResultLauncher() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.getSafeParcelable<User>(TAG_USER)
                    ?.let { data ->
                        viewModel.saveUserInput(data.toUserEntity())
                        binding.etLoginId.setText(data.id)
                        binding.etLoginPassword.setText(data.password)
                    }
            }
        }
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
    companion object {
        const val TAG_USER = "user"
    }
}