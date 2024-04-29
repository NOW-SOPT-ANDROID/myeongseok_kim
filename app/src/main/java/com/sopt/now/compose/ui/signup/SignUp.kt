package com.sopt.now.compose.ui.signup

import android.content.Context
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
import androidx.compose.runtime.getValue
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
import com.sopt.now.compose.component.textfield.TextFieldWithTitle
import com.sopt.now.data.model.UserViewModel
import com.sopt.now.data.model.User
import com.sopt.now.compose.component.toastMessage
import com.sopt.now.compose.navigation.Screen


@Composable
fun SignUp(navHostController: NavHostController, viewModel: UserViewModel) {
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
            title = stringResource(id = R.string.all_mbti),
            value = mbti,
            onValueChanged = { mbti = it },
            description = stringResource(
                id = R.string.singUp_MBTI_hint
            )
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
                        user = User(id, password, nickname, mbti),
                        navHostController = navHostController
                    )
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

private fun signUpButtonEvent(
    context: Context,
    viewModel: UserViewModel,
    user: User,
    navHostController: NavHostController
) {
    if (validateUserInfo(user)) {
        with(viewModel) {
            setMyProfile(user)
        }
        navHostController.navigate(Screen.Login.route)
        context.toastMessage(R.string.singUp_Success)
    } else {
        context.toastMessage(R.string.singUp_failure)
    }
}

private fun validateUserInfo(user: User): Boolean =
    validateID(user.id) && validatePassword(user.password) && validateNickName(user.nickname) && validateMBTI(
        user.mbti
    )

private fun validateID(text: String): Boolean = text.length in 6..10

private fun validatePassword(text: String): Boolean = text.length in 8..12

private fun validateNickName(text: String): Boolean = text.isNotBlank()

private fun validateMBTI(text: String): Boolean = text.length == 4

@Preview(showBackground = true)
@Composable
fun SignUPPreview() {
    val navHostController = rememberNavController()
    SignUp(navHostController = navHostController, viewModel = UserViewModel())
}