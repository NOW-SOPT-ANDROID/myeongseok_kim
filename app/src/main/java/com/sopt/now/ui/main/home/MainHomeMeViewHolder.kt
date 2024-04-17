package com.sopt.now.ui.main.home

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.Profile
import com.sopt.now.databinding.ItemHomeFeedMeBinding

class MainHomeMeViewHolder(private val binding: ItemHomeFeedMeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(userData: Profile.myProfile) {
        binding.run {
            tvHomeFeedMeName.text = userData.name
            tvHomeFeedMeMbti.text = userData.mbti
        }
    }
}