package com.sopt.now.data

sealed class Profile {
    data class myProfile(
        val name: String,
        val number: String
    ) : Profile()

    data class frilendsProfile(
        val name: String,
        val number: String,
    ) : Profile()
}