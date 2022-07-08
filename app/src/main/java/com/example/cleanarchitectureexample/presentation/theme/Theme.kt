package com.example.cleanarchitectureexample.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Gray,
    primaryVariant = DarkGray,
    secondary = Red,
    onPrimary = Color.White,
    background = Color.White,
    surface = Color.White,
    onSurface = Color.DarkGray,
    onBackground = Color.DarkGray
)

private val LightColorPalette = lightColors(
    primary = Gray,
    primaryVariant = DarkGray,
    secondary = Red,
    onPrimary = Color.White,
    background = Color.White,
    surface = Color.White,
    onSurface = Color.DarkGray,
    onBackground = Color.DarkGray

        /* Other default colors to override
        primary = Purple500,
        primaryVariant = Purple700,
        secondary = Teal200
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun CleanArchitectureExampleTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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