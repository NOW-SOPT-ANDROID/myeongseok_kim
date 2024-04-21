package com.sopt.now.feature.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.Profile
import com.sopt.now.databinding.ItemHomeFeedBinding
import com.sopt.now.databinding.ItemHomeFeedMeBinding

class MainHomeAdapter(context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private val userList = mutableListOf<Profile>()

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

    override fun getItemCount(): Int = userList.size

    override fun getItemViewType(position: Int): Int = when (userList[position]) {
        is Profile.myProfile -> ME
        is Profile.frilendsProfile -> FRIENDS
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MainHomeMeViewHolder -> holder.onBind(userList[position] as Profile.myProfile)
            is MainHomeViewHolder -> holder.onBind(userList[position] as Profile.frilendsProfile)
        }
    }

    fun setUserList(dataList: List<Profile>) {
        userList.clear()
        userList.addAll(dataList)
        notifyDataSetChanged()
    }

    companion object {
        const val ME = 0
        const val FRIENDS = 1
    }
}