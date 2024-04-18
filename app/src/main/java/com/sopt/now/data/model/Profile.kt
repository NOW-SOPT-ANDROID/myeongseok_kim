package com.sopt.now.data.model

sealed class Profile {
    data class myProfile(
        val name: String,
        val mbti: String
    ) : Profile()

    data class frilendsProfile(
        val name: String,
        val mbti: String,
    ) : Profile()
}