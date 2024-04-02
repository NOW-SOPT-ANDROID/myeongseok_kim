package com.sopt.now.compose.ui

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when {
                        mainViewModel.screenNumber.value == 0 -> Login(mainViewModel)
                        else -> emptyUnit()
                    }
                }
            }
        }
    }
}

@Composable
fun Login(viewModel: MainViewModel) {
    var id by remember { mutableStateOf("1") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "${viewModel.screenNumber.value}",
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
            label = { Text("사용자 이름 입력") },
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "비밀번호",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 25.sp,

            )
        TextField(
            value = password, onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("비밀번호 입력") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            Arrangement.Bottom
        ) {
            Input_button(text = "회원가입", clickEvent = emptyUnit())
            Button(
                onClick = { loginButtonEvent(viewModel, id, password) },
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



fun emptyUnit() {

}


private fun loginButtonEvent(viewModel: MainViewModel, id: String,password: String) {
    if (viewModel.id.value == id && viewModel.password.value == password) viewModel.setScreen(1)
}

@Composable
fun Input_button(text: String, clickEvent: Unit) {
    Button(
        onClick = { clickEvent},
        modifier = Modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = text)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Login(viewModel = MainViewModel())
}