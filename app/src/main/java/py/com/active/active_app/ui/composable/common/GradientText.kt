package py.com.active.active_app.ui.composable.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import py.com.active.active_app.ui.theme.PrimaryGradient

@Composable
fun GradientText(
    paddingBottom: Dp = 48.dp,
    text: String,
    gradient: Brush = PrimaryGradient,
    fontSize: TextUnit = 24.sp,
) {
    Text(
        text = text,
       fontSize = fontSize,
        style = TextStyle(
            brush = gradient
        ),
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = paddingBottom)
    )
}