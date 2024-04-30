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
                Profile.info("배찬우", "INFP"),
                Profile.info("배찬우", "INFP"),
            )
    }

    fun setMyProfile(data: User) {
        _myProfile.value = data
    }

    fun updateProfileWithMyProfile() {
        _userData.value =
            listOf(

                Profile.info(name = _myProfile.value?.nickname ?:"", mbti = _myProfile.value?.mbti ?:""),
                Profile.info("주효은", "INFP"),
                Profile.info("이유빈", "ENFP"),
                Profile.info("김민우", "ISTP"),
                Profile.info("곽의진", "CUTE"),//자기소개에서 발췌
                Profile.info("유정현", "ESTJ"),
            )
    }
}