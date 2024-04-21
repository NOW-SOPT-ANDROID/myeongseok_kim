package com.sopt.now.feature.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.sopt.now.R
import com.sopt.now.databinding.FragmentMainHomeBinding
import com.sopt.now.feature.main.MainViewModel
import com.sopt.now.core.base.BindingFragment

class MainHomeFragment : BindingFragment<FragmentMainHomeBinding>(R.layout.fragment_main_home) {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var mainHomeAdapter: MainHomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        setUserList()
    }

    private fun initAdapter() {
        mainHomeAdapter = MainHomeAdapter(requireContext())
        binding.rvMainHome.adapter = mainHomeAdapter
    }

    private fun setUserList() {
        viewModel.userData.observe(viewLifecycleOwner) {
            mainHomeAdapter.setUserList(it)
        }
        viewModel.updateProfileWithMyProfile()
    }

}