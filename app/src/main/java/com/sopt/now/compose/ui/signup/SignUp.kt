package com.sopt.now.compose.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sopt.now.compose.R
import com.sopt.now.compose.component.UiState
import com.sopt.now.compose.component.textfield.TextFieldWithTitle
import com.sopt.now.data.model.UserViewModel
import com.sopt.now.compose.component.toastMessage
import com.sopt.now.compose.data.datasource.request.RequestSignUpDto
import com.sopt.now.compose.navigation.Screen


@Composable
fun SignUp(navHostController: NavHostController, viewModel: UserViewModel) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    val context = LocalContext.current
    val authState by viewModel.liveData.observeAsState()

    LaunchedEffect(authState) {
        authState?.let { state ->
            when (state) {
                is UiState.Loading -> {
                }
                is UiState.Success -> {
                    viewModel.setMyProfile(state.data)
                    context.toastMessage(
                        context.getString(R.string.singUp_Success)
                            .format(state.data.userid)
                    )
                    navHostController.navigate(Screen.Login.route)
                }
                is UiState.Error -> {
                    context.toastMessage(state.errorMessage)
                }
            }
        }
    }

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
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextFieldWithTitle(
            title = stringResource(id = R.string.all_id),
            value = id,
            onValueChanged = { id = it },
            description = stringResource(
                id = R.string.all_id_hint
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextFieldWithTitle(
            title = stringResource(id = R.string.all_password),
            value = password,
            onValueChanged = { password = it },
            description = stringResource(
                id = R.string.all_password_hint
            ),
            keyboardType = KeyboardType.Password
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextFieldWithTitle(
            title = stringResource(id = R.string.all_nick_name),
            value = nickname,
            onValueChanged = { nickname = it },
            description = stringResource(
                id = R.string.singUp_nick_name_hint
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextFieldWithTitle(
            title = stringResource(id = R.string.all_number),
            value = number,
            onValueChanged = { number = it },
            description = stringResource(
                id = R.string.singUp_number_hint
            )
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            Arrangement.Bottom
        ) {
            Button(
                onClick = {
                    viewModel.signUp(RequestSignUpDto(authenticationId = id, password = password, nickname = nickname, phone =  number))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = stringResource(id = R.string.all_enroll))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignUPPreview() {
    val navHostController = rememberNavController()
    SignUp(navHostController = navHostController, viewModel = UserViewModel())
}