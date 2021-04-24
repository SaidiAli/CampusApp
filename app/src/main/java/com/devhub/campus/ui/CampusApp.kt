package com.devhub.campus.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.compose.*
import com.devhub.campus.ui.auth.*
import com.devhub.campus.ui.auth.LoginScreen
import com.devhub.campus.ui.auth.MainAuthViewModel
import com.devhub.campus.ui.auth.StartingScreen
import com.devhub.campus.ui.main.Home
import com.devhub.campus.utils.Screen
import com.google.accompanist.systemuicontroller.rememberAndroidSystemUiController

@Composable
fun CampusApp(
    viewModel: MainAuthViewModel
) {
        val navController = rememberNavController()
        val startingDestination: String =
            if(viewModel.user != null) Screen.Feed.route else Screen.StartingScreen.route

        val systemUiController = rememberAndroidSystemUiController()
        val useDarkIcons = MaterialTheme.colors.isLight

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = if(useDarkIcons) Color.White else Color.Black, darkIcons = useDarkIcons
            )
        }

        NavHost(navController, startDestination = startingDestination) {
            composable(Screen.StartingScreen.route) {
                StartingScreen(
                    viewModel = hiltNavGraphViewModel<MainAuthViewModel>(),
                    goToLoginScreen = {
                        navController.navigate(route = Screen.Login.route)
                    },
                    goToRegistrationScreen = {
                        navController.navigate(route = Screen.Register.route,)
                    },
                    goToFeedsScreen = {
                        navController.navigate(route = Screen.Feed.route) {
                            popUpTo(route = Screen.StartingScreen.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Login.route) {
                LoginScreen(
                    viewModel = hiltNavGraphViewModel<MainAuthViewModel>(),
                    goToOtpScreen = {
                        navController.navigate(route = Screen.Feed.route) {
                            popUpTo(route = Screen.StartingScreen.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Register.route) {
                RegistrationScreen(
                    viewModel = hiltNavGraphViewModel<MainAuthViewModel>(),
                    goToProfileScreen = {
                        navController.navigate(route = Screen.Profile.route) {
                            popUpTo(route = Screen.StartingScreen.route) { inclusive = true }
                        }
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
                    goToFeedScreen = {
                        navController.navigate(route = Screen.Feed.route) {
                            popUpTo(route = Screen.Profile.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Feed.route) {
                Home()
            }
        }
}