package com.sopt.now.compose.component.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun TextWithTitle(title: String, contents: String) {
    Text(
        text = title,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth(),
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
    )
    Text(
        text = contents,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 15.sp,
    )
}