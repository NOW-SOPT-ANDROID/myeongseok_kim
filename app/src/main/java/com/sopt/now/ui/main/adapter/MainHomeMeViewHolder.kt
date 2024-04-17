package com.sopt.now.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.User
import com.sopt.now.databinding.ItemHomeFeedMeBinding

class MainHomeMeViewHolder(private val binding: ItemHomeFeedMeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(userData: User) {
        binding.run {
            tvHomeFeedMeName.text = userData.nickname
            tvHomeFeedMeMbti.text = userData.mbti
        }
    }
}