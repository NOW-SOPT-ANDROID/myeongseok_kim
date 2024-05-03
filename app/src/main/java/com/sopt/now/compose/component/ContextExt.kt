package com.sopt.now.compose.component

import android.content.Context
import android.widget.Toast

fun Context.toastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}