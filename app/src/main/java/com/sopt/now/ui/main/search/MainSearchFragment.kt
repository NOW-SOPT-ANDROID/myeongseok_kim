package com.sopt.now.ui.main.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.sopt.now.R
import com.sopt.now.databinding.FragmentMainProfileBinding
import com.sopt.now.ui.main.MainViewModel
import com.sopt.now.ui.main.home.MainHomeAdapter
import com.sopt.now.util.BindingFragment

class MainSearchFragment :
    BindingFragment<FragmentMainProfileBinding>(R.layout.fragment_main_search) {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var mainHomeAdapter: MainHomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
        setUserList()
    }

    private fun initLayout() {
        //추후 추가
    }

    private fun setUserList() {
        //todo 뷰모델 활용한 검색기능
        viewModel.userData.observe(viewLifecycleOwner) {
            mainHomeAdapter.setUserList(it)
        }
    }

}