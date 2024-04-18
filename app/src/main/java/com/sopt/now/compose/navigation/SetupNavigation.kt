package com.sopt.now.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.now.compose.ui.login.Login
import com.sopt.now.compose.ui.home.Home
import com.sopt.now.compose.ui.signup.SignUp
import com.sopt.now.data.model.UserViewModel

@Composable
fun SetupNavigation(navController: NavHostController) {
    val startDest: String = Screen.Login.route
    val userViewModel: UserViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)

    NavHost(
        navController = navController,
        startDestination = startDest
    ) {
        composable(route = Screen.Home.route) {
            Home(navHostController = navController, viewModel = userViewModel)
        }

        composable(route = Screen.Login.route) {
            Login(navHostController = navController, viewModel = userViewModel)
        }

        composable(route = Screen.SignUp.route) {
            SignUp(navHostController = navController, viewModel = userViewModel)
        }
    }
}