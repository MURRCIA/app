package com.bangkok.app.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = BangkokPrimary,
    secondary = BangkokSecondary,
    background = BangkokBackground,
    surface = BangkokSurface,
    onPrimary = BangkokOnPrimary,
    onSecondary = BangkokOnSecondary,
    onBackground = BangkokOnBackground,
    onSurface = BangkokOnSurface,
    error = BangkokError,
    
    // Custom colors for our theme
    primaryContainer = BangkokGrey100,
    secondaryContainer = BangkokGrey200,
    tertiary = BangkokAccentGreen,
    onPrimaryContainer = BangkokGrey800,
    onSecondaryContainer = BangkokGrey700,
    
    // Outline colors
    outline = BangkokGrey600,
    outlineVariant = BangkokGrey300
)

@Composable
fun BangkokTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = LightColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = BangkokTypography,
        content = content
    )
}