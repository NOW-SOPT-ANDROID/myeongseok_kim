package com.sopt.now.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.sopt.now.R
import com.sopt.now.databinding.FragmentMainHomeBinding
import com.sopt.now.ui.main.adapter.MainHomeAdapter
import com.sopt.now.util.BindingFragment

class MainHomeFragment : BindingFragment<FragmentMainHomeBinding>(R.layout.fragment_main_home) {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var mainHomeAdapter: MainHomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        setMyData()
        setUserList()
    }

    private fun initAdapter() {
        Log.d("MainHomeF", "initAdapter: 실행")
        mainHomeAdapter = MainHomeAdapter(requireContext())
        binding.rvMainHome.adapter = mainHomeAdapter
        Log.d("MainHomeF", "initAdapter: 완료")
    }

    private fun setUserList() {
        Log.d("MainHomeF", "setUserList: 실행")
        viewModel.userData.observe(viewLifecycleOwner) { userdata ->
            mainHomeAdapter.setUserList(userdata)
        }
        Log.d("MainHomeF", "setUserList: 완료")
    }

    private fun setMyData() {
        Log.d("MainHomeF", "setMyData: 실행")
        viewModel.myData.observe(viewLifecycleOwner) { myData ->
            mainHomeAdapter.setMyData(myData)
        }
        Log.d("MainHomeF", "setMyData: 완료")
    }


}