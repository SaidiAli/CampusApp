package com.devhub.campus.ui.profile

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.devhub.campus.ui.Center

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel
) {
    val scrollState = rememberScrollState()

    ProfileScreenBody(
        modifier = Modifier,
        state = scrollState,
        vm = viewModel
    )
}