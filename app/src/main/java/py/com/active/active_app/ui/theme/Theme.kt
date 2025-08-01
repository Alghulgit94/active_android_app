package py.com.active.active_app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFF8B0000),
    onPrimaryContainer = Color(0xFFFFB3B3),

    secondary = Secondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF6A1B9A),
    onSecondaryContainer = Color(0xFFE1BEE7),

    tertiary = Accent,
    onTertiary = Color(0xFF1D1B16),
    tertiaryContainer = Color(0xFFE65100),
    onTertiaryContainer = Color(0xFFFFF3C4),

    error = Error,
    onError = Color.White,
    errorContainer = Color(0xFF8B0000),
    onErrorContainer = Color(0xFFFFB3B3),

    background = BackgroundDark,
    onBackground = TextPrimaryDark,
    surface = SurfaceDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = Color(0xFF151515),
    onSurfaceVariant = TextSecondaryDark,

    outline = Color(0xFF757575),
    outlineVariant = Color(0xFF424242),

    surfaceTint = Primary,
    inverseSurface = Color(0xFFF5F5F5),
    inverseOnSurface = Color(0xFF2E2E2E),
    inversePrimary = Color(0xFF8B0000)
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFEBEE),
    onPrimaryContainer = Color(0xFFB71C1C),

    secondary = Secondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFF3E5F5),
    onSecondaryContainer = Color(0xFF4A148C),

    tertiary = Accent,
    onTertiary = Color(0xFF1D1B16),
    tertiaryContainer = Color(0xFFFFF8E1),
    onTertiaryContainer = Color(0xFFE65100),

    error = Error,
    onError = Color.White,
    errorContainer = Color(0xFFFFEBEE),
    onErrorContainer = Color(0xFFB71C1C),

    background = BackgroundLight,
    onBackground = TextPrimaryLight,
    surface = BackgroundLight,
    onSurface = TextPrimaryLight,
    surfaceVariant = Color(0xFFF5F5F5),
    onSurfaceVariant = TextSecondaryLight,

    outline = Color(0xFFBDBDBD),
    outlineVariant = Color(0xFFE0E0E0),

    surfaceTint = Primary,
    inverseSurface = Color(0xFF1A1A1A),
    inverseOnSurface = Color(0xFFF5F5F5),
    inversePrimary = Color(0xFFFFB3B3)
)

@Composable
fun ActiveAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Cambiado a false para usar siempre nuestros colores
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
           // window.statusBarColor = colorScheme.primary.toArgb()
            //WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// Colores adicionales que no están en Material3 ColorScheme
object ActiveAppExtendedColors {
    val hyperlink: Color
        @Composable get() = Hyperlink

    val hyperlinkVariant: Color
        @Composable get() = HyperlinkVariant

    val success: Color
        @Composable get() = Success

    val warning: Color
        @Composable get() = Warning

    val info: Color
        @Composable get() = Info

    val primaryGradient: Brush
        @Composable get() = PrimaryGradient

    val primaryGradientHorizontal: Brush
        @Composable get() = PrimaryGradientHorizontal

    val accentGradient: Brush
        @Composable get() = AccentGradient
}