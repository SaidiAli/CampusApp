package com.devhub.campus.screens.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devhub.campus.R
import com.devhub.campus.ui.auth.MainAuthViewModel
import com.devhub.campus.ui.auth.components.BigButton
import com.devhub.campus.ui.auth.components.Header
import com.devhub.campus.ui.auth.components.UserTextInput
import com.devhub.campus.ui.theme.CampusTheme

@Composable
fun LoginScreen(
    authViewModel: MainAuthViewModel = viewModel(),
    goToOtpScreen: () -> Unit
) {

    CampusTheme {
        Scaffold {
                contentPadding -> Surface(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Header(
                    text = stringResource(id = R.string.login_page_header).toUpperCase(),
                    style = MaterialTheme.typography.h1
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    UserTextInput(
                        text = authViewModel.name,
                        onValueChanged = authViewModel::getName,
                        labelText = stringResource(id = R.string.username)
                    )
                    UserTextInput(
                        text = authViewModel.password,
                        onValueChanged = authViewModel::getPassword,
                        labelText = stringResource(id = R.string.password),
                        visualTransformation = PasswordVisualTransformation()
                    )
                }
                BigButton(
                    onPressed = { authViewModel.getLoginData(goToOtpScreen) },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.login)
                )
            }
        }
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    // LoginScreen({})
}