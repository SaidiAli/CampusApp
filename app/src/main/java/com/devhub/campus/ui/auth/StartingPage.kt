package com.devhub.campus.ui.auth

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.devhub.campus.R
import com.devhub.campus.services.FirebaseAuthService
import com.devhub.campus.ui.auth.components.BigButton
import com.devhub.campus.ui.auth.components.Header
import com.devhub.campus.ui.theme.CampusTheme
import com.devhub.campus.utils.Constants
import java.util.*

@Composable
fun StartingScreen(
    viewModel: MainAuthViewModel,
    goToLoginScreen: () -> Unit,
    goToRegistrationScreen: () -> Unit,
    goToFeedsScreen: () -> Unit
){

    startUpLogin(viewModel, goToFeedsScreen)

    CampusTheme {
        Scaffold {
                contentPadding -> Surface(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Header(
                    text = stringResource(id = R.string.starting_page_header).uppercase(Locale.getDefault()),
                    style = MaterialTheme.typography.h1
                )
                BigButton(
                    onPressed = goToRegistrationScreen,
                    content = {
                        Text(
                            text = stringResource(id = R.string.create_account),
                            color = Color.White,
                        )
                    }
                )
                Text(
                    stringResource(id = R.string.login_option),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable(onClick = goToLoginScreen),
                    style = MaterialTheme.typography.body1
                )
            }
        }
        }
    }
}

fun startUpLogin(viewModel: MainAuthViewModel, redirect: () -> Unit) {
    // check if a user is already logged in ( cached )

    if(viewModel.user != null) {
        redirect()
    }
}
