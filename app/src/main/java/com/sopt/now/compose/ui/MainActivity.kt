package com.sopt.now.compose.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import com.sopt.now.data.model.MainViewModel
import com.sopt.now.data.model.User
import com.sopt.now.util.MainViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel = ViewModelProvider(
                this,
                MainViewModelFactory()
            )[MainViewModel::class.java]

            NOWSOPTAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (mainViewModel.screenNumber.value) {
                        0 -> SignUp(mainViewModel)
                        1 -> Login(mainViewModel)
                        else -> Main(mainViewModel)
                    }
                }
            }
        }
    }
}

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
                onClick = { loginButtonEvent(context,viewModel, id, password) },
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

@Composable
fun SignUp(viewModel: MainViewModel) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "SIGN UP",
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "ID",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 15.sp,
        )
        TextField(
            value = id, onValueChange = { id = it },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text("아이디를 입력해주세요") },
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "비밀번호",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 15.sp,
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
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "닉네임",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 15.sp,

            )
        TextField(
            value = nickname, onValueChange = { nickname = it },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text("닉네임을 입력해주세요") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "MBTI",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 15.sp,

            )
        TextField(
            value = mbti, onValueChange = { mbti = it },
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text("MBTI를 입력해주세요") },
            singleLine = true
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            Arrangement.Bottom
        ) {
            val context = LocalContext.current
            Button(
                onClick = {
                    signUpButtonEvent(
                        context = context,
                        viewModel = viewModel,
                        user = User(id, password, nickname, mbti)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "회원 가입하기")
            }
        }
    }
}


@Composable
fun Main(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "${viewModel.nickname.value}님의 프로필",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "ID",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
        )
        Text(
            text = viewModel.id.value,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 15.sp,
        )

        Text(
            text = "비밀번호",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
        )
        Text(
            text = viewModel.password.value,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 15.sp,
        )

        Text(
            text = "MBTI",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
        )
        Text(
            text = viewModel.mbti.value,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 15.sp,
        )

    }
}


private fun loginButtonEvent(context: Context,viewModel: MainViewModel, id: String, password: String) {
    if (validateLogin(
            viewModel.id.value,
            viewModel.password.value,
            User(id, password)
        )
    ) {viewModel.setScreen(2)
        toastMessage(context, message = "회원가입에 성공했습니다.")}
}

private fun validateLogin(id: String, password: String, user: User): Boolean {
    if (id == "") return false
    if (password == "") return false
    if (id != user.id || password != user.password) return false
    return true
}


private fun signUpButtonEvent(context: Context, viewModel: MainViewModel, user: User) {
    if (validateUserInfo(user)) {
        viewModel.setId(user.id)
        viewModel.setPassword(user.password)
        viewModel.setNickname(user.nickname)
        viewModel.setMbti(user.mbti)
        viewModel.setScreen(1)
        toastMessage(context, message = "회원가입에 성공했습니다.")
    } else {
        toastMessage(context, message = "회원가입이 불가능합니다.")
    }
}

private fun toastMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

private fun validateUserInfo(user: User): Boolean {
    if (validateID(user.id)) return false
    if (validatePassword(user.password)) return false
    if (validateNickName(user.nickname)) return false
    if (validateMBTI(user.mbti)) return false
    return true
}

private fun validateID(text: String): Boolean {
    return text.length < 6 || text.length > 10
}

private fun validatePassword(text: String): Boolean {
    return text.length < 8 || text.length > 12
}

private fun validateNickName(text: String): Boolean {
    return text.trim().isEmpty()
}

private fun validateMBTI(text: String): Boolean {
    return text.length != 4
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Login(viewModel = MainViewModel())
}