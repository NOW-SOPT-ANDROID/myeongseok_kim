package com.sopt.now.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.ui.login.LoginActivity.Companion.TAG_USER
import com.sopt.now.ui.main.home.MainHomeFragment
import com.sopt.now.ui.main.profile.MainProfileFragment
import com.sopt.now.ui.main.search.MainSearchFragment
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
        clickButtonNavigation()
    }

    private fun initMainFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment == null) replaceFragment(MainHomeFragment())
    }

    private fun clickButtonNavigation() {
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(MainHomeFragment())
                    true
                }

                R.id.menu_mypage -> {
                    replaceFragment(MainProfileFragment())
                    true
                }

                R.id.menu_search -> {
                    replaceFragment(MainSearchFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }

    private fun inputData() {
        val user = intent.getSafeParcelable<User>(TAG_USER) ?: User("", "", "", "")
        viewModel.setMyProfile(user)
    }
}