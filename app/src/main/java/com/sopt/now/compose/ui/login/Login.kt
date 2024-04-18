package com.sopt.now.compose.ui.login

import android.content.Context
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
import com.sopt.now.compose.component.toastMessage
import com.sopt.now.compose.navigation.Screen
import com.sopt.now.data.model.UserViewModel
import com.sopt.now.data.model.User

@Composable
fun Login(navHostController: NavHostController, viewModel: UserViewModel) {
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
            text = stringResource(id = R.string.login_welcome),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        TextFieldWithTitle(
            title = stringResource(id = R.string.all_id),
            value = id,
            onValueChanged = { id = it },
            description = stringResource(id = R.string.all_id_hint)
        )
        Spacer(modifier = Modifier.height(30.dp))
        TextFieldWithTitle(
            title = stringResource(id = R.string.all_password),
            value = password,
            onValueChanged = { password = it },
            description = stringResource(id = R.string.all_password_hint),
            keyboardType = KeyboardType.Password
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            Arrangement.Bottom
        ) {
            Button(
                onClick = {
                    navHostController.navigate(Screen.SignUp.route)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = stringResource(id = R.string.all_enroll))
            }
            val context = LocalContext.current
            Button(
                onClick = {
                    loginButtonEvent(context, viewModel, id, password, navHostController)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = stringResource(id = R.string.all_input))
            }
        }
    }
}

private fun loginButtonEvent(
    context: Context,
    viewModel: UserViewModel,
    id: String,
    password: String,
    navHostController: NavHostController
) {
    if (validateLogin(
            id = viewModel.myInfo.value.id,
            password = viewModel.myInfo.value.password,
            user = User(id, password, "", "")
        )
    ) {
        navHostController.navigate(Screen.Home.route)
        context.toastMessage(message = R.string.login_Success)
    }
}

private fun validateLogin(id: String, password: String, user: User): Boolean =
    validateID(id, user) && validatePassword(password, user)

private fun validateID(id: String, user: User): Boolean = id.isNotBlank() && id == user.id

private fun validatePassword(password: String, user: User): Boolean =
    password.isNotBlank() && password == user.password

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val navCtrl = rememberNavController()
    Login(navCtrl, viewModel = UserViewModel())
}