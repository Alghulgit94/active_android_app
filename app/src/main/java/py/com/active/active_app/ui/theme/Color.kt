package py.com.active.active_app.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Paleta de colores personalizada
val Primary = Color(0xFFF44336)
val PrimaryVariant = Color(0xFFD32F2F)
val Secondary = Color(0xFF9B59B6)
val SecondaryVariant = Color(0xFF8E44AD)
val Accent = Color(0xFFFFC107)
val AccentVariant = Color(0xFFFFB300)

// Colores de fondo
val BackgroundLight = Color(0xFFFFFFFF)
val BackgroundDark = Color(0xFF1A1A1A)
val SurfaceLight = Color(0xFFFAFAFA)
val SurfaceDark = Color(0xFF171717)

// Colores de texto
val TextPrimaryLight = Color(0xFF212121)
val TextPrimaryDark = Color(0xFFFAFAFA)
val TextSecondaryLight = Color(0xFF757575)
val TextSecondaryDark = Color(0xFFBDBDBD)

// Colores especiales
val Hyperlink = Color(0xFF1E88E5)
val HyperlinkVariant = Color(0xFF1976D2)

// Colores de estado
val Success = Color(0xFF4CAF50)
val Warning = Color(0xFFFF9800)
val Error = Color(0xFFF44336)
val Info = Color(0xFF2196F3)

// Gradientes
val PrimaryGradient = Brush.verticalGradient(
    colors = listOf(Primary, Secondary)
)

val PrimaryGradientHorizontal = Brush.horizontalGradient(
    colors = listOf(Primary, Secondary)
)

val AccentGradient = Brush.verticalGradient(
    colors = listOf(Accent, AccentVariant)
)