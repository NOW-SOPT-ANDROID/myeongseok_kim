package com.sopt.now.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.User
import com.sopt.now.databinding.ItemHomeFeedBinding
import com.sopt.now.databinding.ItemHomeFeedMeBinding
import java.lang.RuntimeException

class MainHomeAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private val userList = mutableListOf<User>()
    private lateinit var me :User

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

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> ME
        else -> FRIENDS
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MainHomeMeViewHolder -> holder.onBind(userList[position])
            is MainHomeViewHolder -> holder.onBind(userList[position])
        }
    }

    fun setUserList(dataList: List<User>) {
        userList.clear()
        userList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setMyData(user: User) {
        me = user
        notifyDataSetChanged()
    }

    companion object {
        const val ME = 0
        const val FRIENDS = 1
    }
}