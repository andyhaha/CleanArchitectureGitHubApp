package com.github.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightGitHubColorScheme = lightColorScheme(
    primary = GhLightAccent,
    onPrimary = Color.White,
    primaryContainer = GhLightAccentSubtle,
    onPrimaryContainer = GhLightAccent,
    secondary = GhLightSuccessEmphasis,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFDAFBE1),
    onSecondaryContainer = GhLightSuccess,
    tertiary = GhLightAttention,
    onTertiary = Color.White,
    background = GhLightCanvas,
    onBackground = GhLightFg,
    surface = GhLightCanvas,
    onSurface = GhLightFg,
    surfaceVariant = GhLightCanvasSubtle,
    onSurfaceVariant = GhLightFgMuted,
    surfaceContainerLowest = GhLightCanvas,
    surfaceContainerLow = GhLightCanvasSubtle,
    surfaceContainer = GhLightCanvasSubtle,
    surfaceContainerHigh = GhLightCanvasInset,
    surfaceContainerHighest = GhLightCanvasInset,
    outline = GhLightBorder,
    outlineVariant = GhLightBorderMuted,
    error = GhLightDanger,
    onError = Color.White,
    scrim = Color(0x66000000),
)

private val DarkGitHubColorScheme = darkColorScheme(
    primary = GhDarkAccent,
    onPrimary = Color.White,
    primaryContainer = GhDarkAccentEmphasis,
    onPrimaryContainer = Color.White,
    secondary = GhDarkSuccess,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF0F2A1A),
    onSecondaryContainer = GhDarkSuccess,
    tertiary = GhDarkAttention,
    onTertiary = Color.White,
    background = GhDarkCanvas,
    onBackground = GhDarkFg,
    surface = GhDarkCanvas,
    onSurface = GhDarkFg,
    surfaceVariant = GhDarkCanvasSubtle,
    onSurfaceVariant = GhDarkFgMuted,
    surfaceContainerLowest = GhDarkCanvasInset,
    surfaceContainerLow = GhDarkCanvasSubtle,
    surfaceContainer = GhDarkCanvasSubtle,
    surfaceContainerHigh = GhDarkBorderMuted,
    surfaceContainerHighest = GhDarkBorder,
    outline = GhDarkBorder,
    outlineVariant = GhDarkBorderMuted,
    error = GhDarkDanger,
    onError = Color.White,
    scrim = Color(0x99000000),
)

@Composable
fun GitHubAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkGitHubColorScheme else LightGitHubColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val controller = WindowCompat.getInsetsController(window, view)
            controller.isAppearanceLightStatusBars = !darkTheme
            controller.isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
