package com.devhub.campus.ui.new

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.devhub.campus.ui.AppSurface
import com.devhub.campus.ui.Center

@Composable
fun NewPost(
    contentPadding: PaddingValues
) {
    AppSurface(
        modifier = Modifier.padding(contentPadding)
    ) {
        Text(
            text = "New post",
            style = MaterialTheme.typography.h1,
            color = Color.Cyan,
            modifier = Modifier.fillMaxSize().wrapContentSize()
        )
    }
}