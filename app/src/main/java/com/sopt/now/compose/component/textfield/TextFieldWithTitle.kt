package com.sopt.now.compose.component.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme


@Composable
fun TextFieldWithTitle(
    title: String, value: String,
    onValueChanged: (String) -> Unit,
    description: String,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    Column {
        Text(
            text = title,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        TextField(
            value = value, onValueChange = onValueChanged,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text(description) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldWithTitlePreview() {
    NOWSOPTAndroidTheme {
        Column {
            TextFieldWithTitle(
                title = "title",
                description = "des",
                value = "value",
                onValueChanged = {})
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            TextFieldWithTitle(
                title = "title",
                description = "des",
                value = "value",
                onValueChanged = {})
        }
    }

}

