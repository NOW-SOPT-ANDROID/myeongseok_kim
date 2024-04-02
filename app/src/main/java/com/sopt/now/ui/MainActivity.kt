package com.sopt.now.ui

import android.os.Bundle
import com.sopt.now.R
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.util.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var ID: String
    private lateinit var password: String
    private lateinit var MBTI: String
    private lateinit var nickname: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        ID = intent.getStringExtra("ID").toString()
        password = intent.getStringExtra("password").toString()
        MBTI = intent.getStringExtra("MBTI").toString()
        nickname = intent.getStringExtra("nickname").toString()
        layoutInit()
    }

    private fun layoutInit() {
        binding.tvMainNickname.text = nickname + "님 환영합니다."
        binding.tvMainIdContent.text = ID
        binding.tvMainPasswordContent.text = password
        binding.tvMainMbtiContent.text = MBTI
    }

}