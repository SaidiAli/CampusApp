package com.devhub.campus

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.devhub.campus.ui.CampusApp
import com.devhub.campus.ui.auth.MainAuthActivity
import com.devhub.campus.ui.auth.MainAuthViewModel
import com.devhub.campus.ui.main.Home
import com.devhub.campus.ui.theme.CampusTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberAndroidSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainActivityViewModel by viewModels()

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        if(mainViewModel.redirectToAuthActivity.value) {
            startActivity(Intent(this, MainAuthActivity::class.java))
        } else {
            // update localUser here

            setContent {
                ProvideWindowInsets {
                    CampusTheme {
                        Home()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewApp() {
    // CampusApp()
}
