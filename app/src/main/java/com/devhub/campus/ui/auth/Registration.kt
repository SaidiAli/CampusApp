package com.devhub.campus.screens.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
fun RegistrationScreen(
    authViewModel: MainAuthViewModel = viewModel(),
    goToOtpScreen: () -> Unit
){

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
                    text = stringResource(id = R.string.signup_page_header).toUpperCase(),
                    style = MaterialTheme.typography.h2
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    UserTextInput(
                        text = authViewModel.email,
                        onValueChanged = authViewModel::getEmail,
                        labelText = stringResource(id = R.string.email)
                    )
                    UserTextInput(
                        text = authViewModel.password,
                        onValueChanged = authViewModel::getPassword,
                        labelText = stringResource(id = R.string.password)
                    )
                }
                BigButton(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    // onPressed = goToOtpScreen,
                    onPressed = { authViewModel.getRegistrationData(goToOtpScreen) },
                    text = stringResource(id = R.string.register)
                )
            }
        }
        }
    }
}

@Preview
@Composable
fun PreviewRegistrationScreen() {
    // RegistrationScreen({})
}