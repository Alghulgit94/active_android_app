package py.com.active.active_app.ui.composable.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatAvatar(
    background: Color,
    contentAlignment: Alignment,
    text: String
) {
    Spacer(modifier = Modifier.width(12.dp))

    // User Avatar
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(background),//MaterialTheme.colorScheme.secondary),
        contentAlignment = contentAlignment
    ) {
        Text(
            text = text,//,
            fontSize = 20.sp
        )
    }
}
