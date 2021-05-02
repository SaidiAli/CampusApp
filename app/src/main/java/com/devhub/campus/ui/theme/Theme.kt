package com.devhub.campus.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberAndroidSystemUiController

private val DarkColorPalette = darkColors(
    primary = LightGreen,
    primaryVariant = LightGreen,
    background = Black,
    onBackground = LightGreen,
    onSurface = LightGreen
)

private val LightColorPalette = lightColors(
    primary = LightGreen,
    background = White,
    primaryVariant = LightGreen,
    onBackground = LightGreen,
    onSurface = LightGreen

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun CampusTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
internal fun ThemedPreview(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    CampusTheme(darkTheme = darkTheme) {
        Surface {
            content()
        }
    }
}