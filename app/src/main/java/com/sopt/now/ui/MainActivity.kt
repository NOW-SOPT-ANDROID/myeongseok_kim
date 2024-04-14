package com.sopt.now.ui

import android.os.Bundle
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.ui.LoginActivity.Companion.TAG_USER
import com.sopt.now.util.BindingActivity
import com.sopt.now.util.getSafeParcelable

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        val user = intent.getSafeParcelable<User>(TAG_USER)
        if (user != null) {
            binding.run {
                tvMainNickname.text = WELCOME_TEXT.format(user.id)
                tvMainIdContent.text = user.id
                tvMainPasswordContent.text = user.password
                tvMainMbtiContent.text = user.mbti
            }
        }
    }

    companion object {
        const val WELCOME_TEXT = "%s님 환영합니다."
    }

}