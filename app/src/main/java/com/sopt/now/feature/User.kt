package com.sopt.now.feature

import android.os.Parcelable
import com.sopt.now.domain.entity.UserEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: String,
    var password: String,
    var nickname: String,
    var mbti: String
) : Parcelable {
    fun toUserEntity() = UserEntity(id, password, nickname, mbti)
}