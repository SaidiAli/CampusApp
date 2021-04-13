package com.devhub.campus.ui

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.devhub.campus.screens.auth.*
import com.devhub.campus.ui.auth.MainAuthViewModel
import com.devhub.campus.ui.theme.CampusTheme
import com.devhub.campus.utils.Constants
import com.devhub.campus.utils.Screen

@Composable
fun CampusApp() {
    CampusTheme {
        val navController = rememberNavController()
        NavHost(navController, startDestination = Screen.StartingScreen.route) {
            composable(Screen.StartingScreen.route) {
                StartingScreen(
                    goToLoginScreen = {
                        navController.navigate(route = Screen.Login.route)
                    },
                    goToRegistrationScreen = {
                        navController.navigate(route = Screen.Register.route)
                    }
                )
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    viewModel = hiltNavGraphViewModel<MainAuthViewModel>(),
                    goToOtpScreen = {
                        navController.navigate(route = Screen.Otp.route)
                    }
                )
            }
            composable(Screen.Register.route) {
                RegistrationScreen(
                    viewModel = hiltNavGraphViewModel<MainAuthViewModel>(),
                    goToOtpScreen = {
                        Log.i(Constants.INFO_TAG, "registration hit")
                        navController.navigate(route = Screen.Otp.route)
                    }
                )
            }
            composable(Screen.Otp.route) {
                OtpScreen(
                    viewModel = hiltNavGraphViewModel<MainAuthViewModel>(),
                    goToProfileScreen = {
                        navController.navigate(route = Screen.Profile.route)
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    viewModel = hiltNavGraphViewModel<MainAuthViewModel>(),
                )
            }
        }
    }
}