package com.sopt.now.compose.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Home : Screen("home")
    data object SignUp : Screen("signup")
}