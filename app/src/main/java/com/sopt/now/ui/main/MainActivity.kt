package com.sopt.now.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.sopt.now.R
import com.sopt.now.data.Profile
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.ui.login.LoginActivity.Companion.TAG_USER
import com.sopt.now.ui.main.home.MainHomeAdapter
import com.sopt.now.ui.main.home.MainHomeFragment
import com.sopt.now.util.BindingActivity
import com.sopt.now.util.getSafeParcelable

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var mainHomeAdapter: MainHomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        initMainFragment()
//        initViewmodel()
//        initAdapter()
    }

    private fun initMainFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_main, MainHomeFragment())
                .commit()
        }
    }

    private fun initAdapter() {
        val user = intent.getSafeParcelable<User>(TAG_USER)
        if (user != null) viewModel.inputMyProfile(Profile.myProfile(user.nickname, user.mbti))
    }

    private fun initViewmodel() {
        viewModel.userData.observe(this) { userdata->
            mainHomeAdapter.setUserList(userdata)
        }
    }
}