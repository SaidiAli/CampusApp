package com.devhub.campus.ui.discover

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.devhub.campus.ui.AppSurface

@Composable
fun DiscoverScreen(
    modifier: Modifier = Modifier
) {
    AppSurface(
        modifier = modifier
    ) {
        Text(
            text = "Discover Screen",
            style = MaterialTheme.typography.h1,
            color = Color.Cyan,
            modifier = modifier.fillMaxSize().wrapContentSize()
        )
    }
}