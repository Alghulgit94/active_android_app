package py.com.active.active_app.ui.composable.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import py.com.active.active_app.ui.theme.PrimaryGradientHorizontal

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppBar() {
    TopAppBar(
        title = {
            GradientText(
                text = "active.ai",
                fontSize = 20.sp,
                paddingBottom = 0.dp,
                gradient = PrimaryGradientHorizontal
            )
        },
        actions = {
            IconButton(onClick = { /* Handle settings */ }) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    )
}