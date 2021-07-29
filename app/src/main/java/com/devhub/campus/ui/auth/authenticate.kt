package com.devhub.campus.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devhub.campus.R
import com.devhub.campus.ui.Center
import com.devhub.campus.ui.auth.components.Header
import com.devhub.campus.ui.theme.CampusTheme
import com.devhub.campus.ui.theme.ThemedPreview
import java.util.*

@Composable
fun AuthScreen(
    viewModel: MainAuthViewModel,
    authenticate: () -> Unit,
) {

    val context = LocalContext.current

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
                    text = stringResource(id = R.string.auth_screen_header).uppercase(Locale.ROOT),
                    style = MaterialTheme.typography.h1,
                    smallText = stringResource(id = R.string.login_sub)
                )
                Center(modifier = Modifier) {
                    Button(
                        onClick = authenticate,
                        modifier = Modifier
                            .padding(24.dp)
                            .height(48.dp)
                            .fillMaxSize(),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Google")
                    }
                }
            }
        }
        }
    }
}

@Preview
@Composable
fun PreviewAuthScreen() {
    CampusTheme {
        // AuthScreen()
    }
}

@Preview
@Composable
fun PreviewAuthScreenDark() {
    ThemedPreview(darkTheme = true) {
        // AuthScreen()
    }
}