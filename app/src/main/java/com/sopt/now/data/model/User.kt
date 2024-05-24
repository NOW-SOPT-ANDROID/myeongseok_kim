package com.sopt.now.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: String,
    var password: String,
    var nickname: String,
    var phoneNumber: String,
    var userId: String = "",
) : Parcelable