package com.devhub.campus.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.devhub.campus.MainActivity
import com.devhub.campus.services.AuthManager
import com.devhub.campus.ui.auth.ui.theme.CampusTheme
import com.devhub.campus.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SetupProfileActivity : ComponentActivity() {
    private val viewModel: MainAuthViewModel by viewModels()

    @Inject
    lateinit var authManager: AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listenToViewModelState()

        setContent {
            CampusTheme {
                SetupProfileScreen(
                    viewModel = viewModel,
                )
            }
        }
    }

    private fun listenToViewModelState() {
        viewModel.profileCreated.observe(this, EventObserver{
            if (it) startActivity(Intent(this, MainActivity::class.java))
        })
    }
}
