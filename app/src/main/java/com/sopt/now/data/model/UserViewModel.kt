package com.sopt.now.data.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.R

class UserViewModel() : ViewModel() {
    private val _userData = mutableStateListOf<Profile.FriendProfile>()
    val userData = _userData

    private val _myProfile = mutableStateOf(
        Profile.MyProfile(
            R.drawable.img_profile,
            "에러!",
            "에러!"
        )
    )
    val myProfile = _myProfile

    private val _myInfo = mutableStateOf(User("", "", "", ""))
    val myInfo = _myInfo

    init {
        _userData.addAll(
            listOf(
                Profile.FriendProfile(R.drawable.img_profile, "주효은", "INFP"),
                Profile.FriendProfile(R.drawable.img_profile, "이유빈", "ENFP"),
                Profile.FriendProfile(R.drawable.img_profile, "김민우", "ISTP"),
                Profile.FriendProfile(R.drawable.img_profile, "곽의진", "CUTE"),//자기소개에서 발췌
                Profile.FriendProfile(R.drawable.img_profile, "유정현", "ESTJ"),
                Profile.FriendProfile(R.drawable.img_profile, "주효은", "INFP"),
                Profile.FriendProfile(R.drawable.img_profile, "이유빈", "ENFP"),
                Profile.FriendProfile(R.drawable.img_profile, "김민우", "ISTP"),
                Profile.FriendProfile(R.drawable.img_profile, "곽의진", "CUTE"),//자기소개에서 발췌
                Profile.FriendProfile(R.drawable.img_profile, "유정현", "ESTJ"),
                Profile.FriendProfile(R.drawable.img_profile, "주효은", "INFP"),
                Profile.FriendProfile(R.drawable.img_profile, "이유빈", "ENFP"),
                Profile.FriendProfile(R.drawable.img_profile, "김민우", "ISTP"),
                Profile.FriendProfile(R.drawable.img_profile, "곽의진", "CUTE"),//자기소개에서 발췌
                Profile.FriendProfile(R.drawable.img_profile, "유정현", "ESTJ"),
                Profile.FriendProfile(R.drawable.img_profile, "주효은", "INFP"),
                Profile.FriendProfile(R.drawable.img_profile, "이유빈", "ENFP"),
                Profile.FriendProfile(R.drawable.img_profile, "김민우", "ISTP"),
                Profile.FriendProfile(R.drawable.img_profile, "곽의진", "CUTE"),//자기소개에서 발췌
                Profile.FriendProfile(R.drawable.img_profile, "유정현", "ESTJ"),


            )
        )
    }

    fun setMyProfile(data: User) {
        _myInfo.value = data
        _myProfile.value = Profile.MyProfile(
            R.drawable.img_profile,
            data.nickname,
            data.mbti
        )
    }

    fun updateUserData(friends: List<Profile.FriendProfile>) {
        _userData.clear()
        _userData.addAll(friends)
    }
}