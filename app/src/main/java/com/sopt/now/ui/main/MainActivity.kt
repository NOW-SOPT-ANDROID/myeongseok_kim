package com.sopt.now.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.sopt.now.R
import com.sopt.now.data.Profile
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.ui.login.LoginActivity.Companion.TAG_USER
import com.sopt.now.ui.main.home.MainHomeFragment
import com.sopt.now.util.BindingActivity
import com.sopt.now.util.getSafeParcelable

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        initMainFragment()
        inputData()
    }

    private fun initMainFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_main, MainHomeFragment())
                .commit()
        }
    }

    private fun inputData() {
        val user = intent.getSafeParcelable<User>(TAG_USER)
        if (user != null) viewModel.inputMyProfile(user)
    }

}