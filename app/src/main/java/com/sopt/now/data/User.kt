package com.sopt.now.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: String,
    var password: String,
    var nickname: String,
    var phonenumber: String
) : Parcelable