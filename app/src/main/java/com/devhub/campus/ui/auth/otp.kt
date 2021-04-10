package com.devhub.campus.screens.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devhub.campus.R
import com.devhub.campus.ui.auth.components.BigButton
import com.devhub.campus.ui.auth.components.Header
import com.devhub.campus.ui.auth.components.UserTextInput
import com.devhub.campus.ui.theme.CampusTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devhub.campus.ui.auth.MainAuthViewModel

@Composable
fun OtpScreen(
    authViewModel: MainAuthViewModel = viewModel(),
    goToProfileScreen: () -> Unit = {}
){
    CampusTheme {
        var value by remember { mutableStateOf("")}

        Scaffold {
                contentPadding -> Surface(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Header(
                    text = stringResource(id = R.string.otp_page_header).toUpperCase(),
                    style = MaterialTheme.typography.h2
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    UserTextInput(
                        text = value,
                        onValueChanged = {
                            value = it
                        },
                        labelText = stringResource(id = R.string.otp),
                    )
                }
                BigButton(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onPressed = { authViewModel.handleOtpCodeVerification(goToProfileScreen) },
                    text = stringResource(id = R.string.verification)
                )
            }
        }
        }
    }
}

@Preview
@Composable
fun PreviewOtpScreen() {
    // OtpScreen({})
}