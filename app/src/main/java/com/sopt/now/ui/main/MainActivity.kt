package com.sopt.now.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.ui.login.LoginActivity.Companion.TAG_USER
import com.sopt.now.ui.main.adapter.MainHomeAdapter
import com.sopt.now.util.BindingActivity
import com.sopt.now.util.getSafeParcelable

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var mainHomeAdapter: MainHomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMainFragment()
        initAdapter()
        initViewmodel()
        initLayout()
    }

    private fun initLayout() {
        val user = intent.getSafeParcelable<User>(TAG_USER)
        if (user != null) mainHomeAdapter.setMyData(
            User(
                user.id,
                user.password,
                user.nickname,
                user.mbti
            )
        )
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
        mainHomeAdapter = MainHomeAdapter(this)
    }

    private fun initViewmodel() {
        viewModel.myData.observe(this) { myData ->
            mainHomeAdapter.setMyData(myData)
        }
        viewModel.userData.observe(this) { userData ->
            mainHomeAdapter.setUserList(userData)
        }
    }

    companion object {
        const val WELCOME_TEXT = "%s님 환영합니다."
    }

}