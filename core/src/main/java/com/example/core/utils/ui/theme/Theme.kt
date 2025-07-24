package com.example.core.utils.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.core.utils.ui.MainColorType

private val GreenDarkColorScheme = darkColorScheme(
    primary = PrimaryGreen,
    secondary = SecondaryGreen,
    tertiary = Purple80,
    background = DimGray,
    surface = DimGray,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    primaryContainer = PrimaryGreen,
    secondaryContainer = SecondaryGreen,
    onSecondaryContainer = PrimaryGreen
)

private val GreenLightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    primaryContainer = PrimaryGreen,
    secondaryContainer = SecondaryGreen,
    onSecondaryContainer = PrimaryGreen,
    surface = Grey
)

private val YellowDarkColorScheme = darkColorScheme(
    primary = PrimaryYellow,
    secondary = SecondaryYellow,
    tertiary = Purple80,
    background = DimGray,
    surface = DimGray,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    primaryContainer = PrimaryYellow,
    secondaryContainer = SecondaryYellow,
    onSecondaryContainer = PrimaryYellow
)

private val YellowLightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    primaryContainer = PrimaryYellow,
    secondaryContainer = SecondaryYellow,
    onSecondaryContainer = PrimaryYellow,
    surface = Grey
)

private val RedDarkColorScheme = darkColorScheme(
    primary = PrimaryRed,
    secondary = SecondaryRed,
    tertiary = Purple80,
    background = DimGray,
    surface = DimGray,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    primaryContainer = PrimaryRed,
    secondaryContainer = SecondaryRed,
    onSecondaryContainer = PrimaryRed
)

private val RedLightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    primaryContainer = PrimaryRed,
    secondaryContainer = SecondaryRed,
    onSecondaryContainer = PrimaryRed,
    surface = Grey
)



@Composable
fun SHMRYandexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    mainColor: MainColorType,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when (mainColor) {
        MainColorType.GREEN -> {
            when (darkTheme) {
                true -> GreenDarkColorScheme
                false -> GreenLightColorScheme
            }
        }
        MainColorType.RED -> when (darkTheme) {
            true -> RedDarkColorScheme
            false -> RedLightColorScheme
        }
        MainColorType.YELLOW -> when (darkTheme) {
            true -> YellowDarkColorScheme
            false -> YellowLightColorScheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}