package com.devhub.campus.ui.auth

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.devhub.campus.R
import com.devhub.campus.ui.auth.components.BigButton
import com.devhub.campus.ui.auth.components.Header
import com.devhub.campus.ui.auth.components.UserTextInput
import com.devhub.campus.ui.theme.CampusTheme

@Composable
fun LoginScreen(
    viewModel: MainAuthViewModel,
    goToOtpScreen: () -> Unit
) {

    val context = LocalContext.current

    listenToViewModelStateLogin(viewModel, context)

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
                    style = MaterialTheme.typography.h1,
                    smallText = stringResource(id = R.string.login_sub)
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    UserTextInput(
                        text = viewModel.email,
                        onValueChanged = viewModel::getEmail,
                        labelText = stringResource(id = R.string.email)
                    )
                    UserTextInput(
                        text = viewModel.password,
                        onValueChanged = viewModel::getPassword,
                        labelText = stringResource(id = R.string.password),
                        visualTransformation = PasswordVisualTransformation()
                    )
                }
                BigButton(
                    onPressed = { viewModel.getLoginData(goToOtpScreen) },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = if(viewModel.loadingState) stringResource(id = R.string.loading) else stringResource(id = R.string.login)
                )
            }
        }
        }
    }
}

fun listenToViewModelStateLogin(viewModel: MainAuthViewModel, context: Context) {
    if (viewModel.showErrorToast) {
        Toast.makeText(context, viewModel.errorMessage, Toast.LENGTH_SHORT).show()
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    // LoginScreen({})
}