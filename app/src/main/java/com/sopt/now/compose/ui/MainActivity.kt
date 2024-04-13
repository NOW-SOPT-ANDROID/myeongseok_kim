package com.sopt.now.compose.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.component.text.TextWithTitle
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import com.sopt.now.data.model.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel: MainViewModel by viewModels()
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
            text = stringResource(id = R.string.main_title).format(viewModel.nickname.value),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextWithTitle(
            title = stringResource(id = R.string.all_id),
            contents = viewModel.id.value
        )
        TextWithTitle(
            title = stringResource(id = R.string.all_password),
            contents = viewModel.password.value
        )
        TextWithTitle(
            title = stringResource(id = R.string.all_mbti),
            contents = viewModel.mbti.value
        )

    }
}

fun toastMessage(context: Context, message: Int) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

