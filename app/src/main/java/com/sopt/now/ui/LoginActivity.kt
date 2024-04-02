package com.sopt.now.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.sopt.now.R
import com.sopt.now.databinding.ActivityLoginBinding

import com.sopt.now.util.BindingActivity

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var ID = ""
    private var password = ""
    private var MBTI = ""
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
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }

        binding.btnLogin.setOnClickListener {
            if (validateLogin()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("ID", ID)
                intent.putExtra("password", password)
                intent.putExtra("nickname", nickname)
                intent.putExtra("MBTI", MBTI)
                startActivity(intent)
            }
        }
    }

    private fun setResultNext() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                ID = result.data?.getStringExtra("ID") ?: ""
                password = result.data?.getStringExtra("password") ?: ""
                nickname = result.data?.getStringExtra("nickname") ?: ""
                MBTI = result.data?.getStringExtra("MBTI") ?: ""
                binding.etLoginId.setText(ID)
                binding.etLoginPassword.setText(password)
            }
        }
    }

    private fun validateLogin(): Boolean {
        if (ID == "" || password == "") {
            Toast.makeText(this, "회원정보가 없습니다.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.etLoginId.text.toString() != ID) {
            Toast.makeText(this, "아이디가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.etLoginPassword.text.toString() != password) {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}