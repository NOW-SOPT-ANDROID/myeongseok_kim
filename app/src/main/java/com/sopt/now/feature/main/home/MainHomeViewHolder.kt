package com.sopt.now.feature.main.home

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.Profile
import com.sopt.now.databinding.ItemHomeFeedBinding

class MainHomeViewHolder(private val binding: ItemHomeFeedBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(userData: Profile.frilendsProfile) {
        binding.run {
            tvHomeFeedName.text = userData.name
            tvHomeFeedMbti.text = userData.mbti
        }
    }
}

