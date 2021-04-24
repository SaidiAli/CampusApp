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
    viewModel: MainAuthViewModel,
    goToProfileScreen: () -> Unit
){

    val context = LocalContext.current

    listenToViewModelState(viewModel, context)

    CampusTheme {
        Scaffold{
                contentPadding -> Surface(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                BottomAppBar() {

                }
                Header(
                    text = stringResource(id = R.string.signup_page_header).toUpperCase(),
                    style = MaterialTheme.typography.h2,
                    smallText = stringResource(id = R.string.registration_sub)
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    UserTextInput(
                        text = viewModel.name,
                        onValueChanged = viewModel::getName,
                        labelText = stringResource(id = R.string.name)
                    )
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
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onPressed = { viewModel.getRegistrationData(goToProfileScreen) },
                    text =
                    if(viewModel.loadingState)
                        stringResource(id = R.string.loading)
                    else
                        stringResource(id = R.string.register)
                )
            }
        }
        }
    }
}

fun listenToViewModelState(viewModel: MainAuthViewModel, context: Context) {
    if (viewModel.showErrorToast) {
        Toast.makeText(context, viewModel.errorMessage, Toast.LENGTH_SHORT).show()
    }
}

@Preview
@Composable
fun PreviewRegistrationScreen() {
    // RegistrationScreen({})
}