package com.devhub.campus.screens.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devhub.campus.R
import com.devhub.campus.ui.auth.MainAuthViewModel
import com.devhub.campus.ui.auth.components.BigButton
import com.devhub.campus.ui.auth.components.Header
import com.devhub.campus.ui.theme.CampusTheme

@Composable
fun StartingScreen(
    goToLoginScreen: () -> Unit,
    goToRegistrationScreen: () -> Unit
){
    CampusTheme {
        Scaffold {
                contentPadding -> Surface(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Header(
                    text = stringResource(id = R.string.starting_page_header).toUpperCase(),
                    style = MaterialTheme.typography.h1
                )
                BigButton(
                    onPressed = goToRegistrationScreen,
                    text = stringResource(id = R.string.create_account)
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

@Preview
@Composable
fun PreviewStartingPage() {
    StartingScreen({}, {})
}