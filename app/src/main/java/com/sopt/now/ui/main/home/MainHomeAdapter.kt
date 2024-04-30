package com.sopt.now.ui.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.Profile
import com.sopt.now.databinding.ItemHomeFeedBinding
import com.sopt.now.databinding.ItemHomeFeedMeBinding

class MainHomeAdapter(context: Context) :
    ListAdapter<Profile, RecyclerView.ViewHolder>(ProfileDiffCallback()) {
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ME -> MainHomeMeViewHolder(
                ItemHomeFeedMeBinding.inflate(
                    inflater, parent, false
                )
            )

            FRIENDS -> MainHomeViewHolder(
                ItemHomeFeedBinding.inflate(
                    inflater, parent, false
                )
            )

            else -> throw RuntimeException("viewType Error")
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return ME
        return FRIENDS
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MainHomeMeViewHolder -> holder.onBind(getItem(position) as Profile.info)
            is MainHomeViewHolder -> holder.onBind(getItem(position) as Profile.info)
        }
    }

    companion object {
        const val ME = 0
        const val FRIENDS = 1
    }
}

class ProfileDiffCallback : DiffUtil.ItemCallback<Profile>() {
    override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
        return oldItem == newItem
    }
}
