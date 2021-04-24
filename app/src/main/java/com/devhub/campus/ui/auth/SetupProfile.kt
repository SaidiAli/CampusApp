package com.devhub.campus.ui.auth

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
    viewModel: MainAuthViewModel,
    goToFeedScreen: () -> Unit
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
                    style = MaterialTheme.typography.h2,
                    smallText = stringResource(id = R.string.profile_sub)
                )
                Column(modifier = Modifier.fillMaxWidth()) {
                    UserTextInput(
                        text = viewModel.userName,
                        onValueChanged = viewModel::getUsername,
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
                }
                BigButton(
                    onPressed = { viewModel.createProfile(goToFeedScreen) },
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