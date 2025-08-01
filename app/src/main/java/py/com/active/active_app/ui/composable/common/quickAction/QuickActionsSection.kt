package py.com.active.active_app.ui.composable.common.quickAction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import py.com.active.active_app.ui.composable.chat.QuickAction

@Composable
fun QuickActionsSection(
    quickActions: List<QuickAction>,
    onQuickActionClick: (QuickAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Primera línea - primeros 2 actions
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            quickActions.take(2).forEach { action ->
                QuickActionCard(
                    action = action,
                    onClick = { onQuickActionClick(action) }
                )
            }
        }

        // Segunda línea - siguientes 2 actions
        if (quickActions.size > 2) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                quickActions.drop(2).take(2).forEach { action ->
                    QuickActionCard(
                        action = action,
                        onClick = { onQuickActionClick(action) }
                    )
                }
            }
        }
    }
}