package com.devhub.campus.utils

sealed class Screen(val route: String) {
    object StartingScreen : Screen("starting")
    object Feed : Screen("feed")
    object Login : Screen("login")
    object Register : Screen("register")
    object Otp : Screen("otp")
    object Profile : Screen("profile")
    object Auth: Screen("auth_screen")
}