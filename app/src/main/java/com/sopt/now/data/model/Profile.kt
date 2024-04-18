package com.sopt.now.data.model

sealed class Profile {
    data class MyProfile(
        val profileImage: Int,
        val name: String,
        val mbti: String
    ) : Profile()

    data class FriendProfile(
        val profileImage: Int,
        val name: String,
        val mbti: String,
    ) : Profile()
}