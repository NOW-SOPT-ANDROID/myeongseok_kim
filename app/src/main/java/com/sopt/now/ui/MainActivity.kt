package com.sopt.now.ui

import android.os.Build
import android.os.Bundle
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.util.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        layoutInit()
    }

    private fun layoutInit() {
        val user: User? by lazy {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent?.getParcelableExtra(
                    TAG_USER, User::class.java
                )
            } else {
                intent?.getParcelableExtra(
                    TAG_USER
                )
            }
        }
        binding.run {
            tvMainNickname.text = user?.nickname + "님 환영합니다."
            tvMainIdContent.text = user?.id
            tvMainPasswordContent.text = user?.password
            tvMainMbtiContent.text = user?.mbti
        }
    }

    companion object {
        const val TAG_USER ="user"
    }

}