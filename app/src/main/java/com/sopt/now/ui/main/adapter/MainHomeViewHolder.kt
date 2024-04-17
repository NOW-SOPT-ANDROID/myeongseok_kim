package com.sopt.now.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.User
import com.sopt.now.databinding.ItemHomeFeedBinding

class MainHomeViewHolder(private val binding: ItemHomeFeedBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(userData: User) {
        binding.run {
            tvHomeFeedName.text = userData.nickname
            tvHomeFeedMbti.text = userData.mbti
        }
    }
}

