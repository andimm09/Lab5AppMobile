package com.example.lab5appmobile.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.lab5appmobile.data.Category

// Paletă inspirată de Maramureș: verde de pădure, lemn și ocru.
private val LightColors = lightColorScheme(
    primary = Color(0xFF2E6B4F),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFB4F1CF),
    onPrimaryContainer = Color(0xFF002114),
    secondary = Color(0xFF8A5A2B),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFDCC1),
    onSecondaryContainer = Color(0xFF2E1500),
    tertiary = Color(0xFF3D6373),
    background = Color(0xFFF7F8F3),
    surface = Color(0xFFF7F8F3),
    surfaceVariant = Color(0xFFDDE5DC),
    surfaceContainer = Color(0xFFECEFE8),
    surfaceContainerLow = Color(0xFFF1F4EE),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF98D5B3),
    onPrimary = Color(0xFF003824),
    primaryContainer = Color(0xFF115238),
    onPrimaryContainer = Color(0xFFB4F1CF),
    secondary = Color(0xFFFFB878),
    onSecondary = Color(0xFF4B2800),
    secondaryContainer = Color(0xFF6C4216),
    onSecondaryContainer = Color(0xFFFFDCC1),
    tertiary = Color(0xFFA5CCDF),
    background = Color(0xFF101411),
    surface = Color(0xFF101411),
    surfaceContainer = Color(0xFF1C211D),
    surfaceContainerLow = Color(0xFF181D19),
)

@Composable
fun GuideTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) DarkColors else LightColors,
        content = content,
    )
}

/** Culoare de accent pe categorie (pentru etichete și imaginile lipsă). */
fun Category.accent(): Color = when (this) {
    Category.SPORT -> Color(0xFF2F7DA8)
    Category.MUZEE -> Color(0xFF8A5A2B)
    Category.RESTAURANTE -> Color(0xFFB0413E)
}
