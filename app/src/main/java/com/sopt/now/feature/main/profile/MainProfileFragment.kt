package com.sopt.now.feature.main.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.sopt.now.R
import com.sopt.now.databinding.FragmentMainProfileBinding
import com.sopt.now.feature.main.MainViewModel
import com.sopt.now.core.base.BindingFragment

class MainProfileFragment :
    BindingFragment<FragmentMainProfileBinding>(R.layout.fragment_main_profile) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        with(binding) {
            tvMainIdContent.text = viewModel.myProfile.value?.id
            tvMainPasswordContent.text = viewModel.myProfile.value?.password
            tvMainNickname.text = WELCOME_TEXT.format(viewModel.myProfile.value?.nickname)
            tvMainMbtiContent.text = viewModel.myProfile.value?.mbti
        }
    }

    companion object {
        const val WELCOME_TEXT = "%s님 환영합니다."
    }
}