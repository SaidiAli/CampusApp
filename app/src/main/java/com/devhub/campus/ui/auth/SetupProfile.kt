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
    authViewModel: MainAuthViewModel = viewModel()
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
                        text = authViewModel.name,
                        onValueChanged = authViewModel::getName,
                        labelText = stringResource(id = R.string.username),
                    )
                    UserTextInput(
                        text = authViewModel.campusName,
                        onValueChanged = authViewModel::getCampusName,
                        labelText = stringResource(id = R.string.campus_name),
                    )
                    UserTextInput(
                        text = authViewModel.programOfStudy,
                        onValueChanged = authViewModel::getPOS,
                        labelText = stringResource(id = R.string.pos),
                    )
                    UserTextInput(
                        text = authViewModel.password,
                        onValueChanged = authViewModel::getPassword,
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