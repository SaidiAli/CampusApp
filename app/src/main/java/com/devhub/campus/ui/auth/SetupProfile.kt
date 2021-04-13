package com.devhub.campus.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devhub.campus.R
import com.devhub.campus.ui.auth.MainAuthViewModel
import com.devhub.campus.ui.auth.components.BigButton
import com.devhub.campus.ui.auth.components.Header
import com.devhub.campus.ui.auth.components.UserTextInput

@Composable
fun ProfileScreen(
    viewModel: MainAuthViewModel
){
        Scaffold {
                contentPadding -> Surface(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround
            ) {

                Header(
                    text = stringResource(id = R.string.profile_page_header).toUpperCase(),
                    style = MaterialTheme.typography.h2
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    UserTextInput(
                        text = viewModel.name,
                        onValueChanged = viewModel::getName,
                        labelText = stringResource(id = R.string.username),
                    )
                    UserTextInput(
                        text = viewModel.campusName,
                        onValueChanged = viewModel::getCampusName,
                        labelText = stringResource(id = R.string.campus_name),
                    )
                    UserTextInput(
                        text = viewModel.programOfStudy,
                        onValueChanged = viewModel::getPOS,
                        labelText = stringResource(id = R.string.pos),
                    )
                    UserTextInput(
                        text = viewModel.password,
                        onValueChanged = viewModel::getPassword,
                        labelText = stringResource(id = R.string.password),
                        visualTransformation = PasswordVisualTransformation()
                    )
                }
                BigButton(
                    onPressed = { /* TODO: complete user registration and navigate to feeds page */ },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.finish)
                )
            }
        }
        }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    // ProfileScreen()
}