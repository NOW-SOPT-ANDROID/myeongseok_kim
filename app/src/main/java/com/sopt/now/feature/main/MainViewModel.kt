package com.sopt.now.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.Profile
import com.sopt.now.feature.User

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

    fun setMyProfile(data: User) {
        _myProfile.value = data
    }

    fun updateProfileWithMyProfile() {
        _userData.value =
            listOf(
                Profile.myProfile(_myProfile.value!!.nickname, _myProfile.value!!.mbti),
                Profile.frilendsProfile("주효은", "INFP"),
                Profile.frilendsProfile("이유빈", "ENFP"),
                Profile.frilendsProfile("김민우", "ISTP"),
                Profile.frilendsProfile("곽의진", "CUTE"),//자기소개에서 발췌
                Profile.frilendsProfile("유정현", "ESTJ"),
            )
    }
}