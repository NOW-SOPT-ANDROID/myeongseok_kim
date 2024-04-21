package com.sopt.now.core.util

import android.content.Intent
import android.os.Build
import android.os.Parcelable

inline fun <reified T : Parcelable> Intent.getSafeParcelable(tag: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(tag, T::class.java)
    } else {
        getParcelableExtra(tag) as? T
    }
}
