package com.sopt.now.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.Profile
import com.sopt.now.data.User

class MainViewModel : ViewModel() {
    private val _userData = MutableLiveData<List<Profile>>()
    val userData = _userData

    private val _myProfile = MutableLiveData<User>()
    val myProfile = _myProfile

    init {
        _userData.value =
            listOf(
                Profile.frilendsProfile("배찬우", "INFP"),
                Profile.frilendsProfile("배찬우", "INFP"),
            )
    }

    fun inputMyProfile(data: User) {
        _myProfile.value = data
        _userData.value =
            listOf(
                Profile.myProfile(data.nickname, data.mbti),
                Profile.frilendsProfile(
                    "배찬우", "INFP"
                ),
                Profile.frilendsProfile("배찬우", "INFP"),
            )
    }
}