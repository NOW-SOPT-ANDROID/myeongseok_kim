package com.sopt.now.compose.ui

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.data.model.MainViewModel
import com.sopt.now.data.model.User


@Composable
fun Login(viewModel: MainViewModel) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Welcome to SOPT",
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "ID",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
        )
        TextField(
            value = id, onValueChange = { id = it },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text("사용자 이름 입력") },
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "비밀번호",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,

            )
        TextField(
            value = password, onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text("비밀번호 입력") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            Arrangement.Bottom
        ) {
            Button(
                onClick = { viewModel.setScreen(0) },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "회원가입")
            }
            val context = LocalContext.current
            Button(
                onClick = { loginButtonEvent(context, viewModel, id, password) },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "입력하기")
            }
        }
    }
}

private fun loginButtonEvent(
    context: Context,
    viewModel: MainViewModel,
    id: String,
    password: String
) {
    if (validateLogin(
            viewModel.id.value,
            viewModel.password.value,
            User(id, password)
        )
    ) {
        viewModel.setScreen(2)
        toastMessage(context, message = "회원가입에 성공했습니다.")
    }
}

private fun validateLogin(id: String, password: String, user: User): Boolean {
    if (id == "") return false
    if (password == "") return false
    if (id != user.id || password != user.password) return false
    return true
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Login(viewModel = MainViewModel())
}