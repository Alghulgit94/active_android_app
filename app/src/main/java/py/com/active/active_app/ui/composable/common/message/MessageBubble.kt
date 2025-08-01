package py.com.active.active_app.ui.composable.common.message

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import py.com.active.active_app.ui.composable.chat.ChatAvatar
import py.com.active.active_app.ui.composable.chat.Message
import py.com.active.active_app.ui.composable.chat.FormType
import py.com.active.active_app.ui.composable.common.CheckBoxForm
import py.com.active.active_app.ui.composable.common.RadioButtonForm

@Composable
fun MessageBubble(
    message: Message,
    onRadioButtonSelected: (String, Int, String) -> Unit, // Agregamos messageId
    onCheckBoxSelected: (String, Int, String) -> Unit,     // Agregamos messageId
    onConfirmCheckboxSelection: ((String) -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isFromUser) Arrangement.End else Arrangement.Start
    ) {
        if (!message.isFromUser) {
            ChatAvatar(
                background = MaterialTheme.colorScheme.tertiary,
                contentAlignment = Alignment.Center,
                text = "🤖"
            )
            Spacer(modifier = Modifier.width(12.dp))
        }

        Column(
            horizontalAlignment = if (message.isFromUser) Alignment.End else Alignment.Start
        ) {
            if (message.isFromUser && message.senderName != null) {
                Text(
                    text = message.senderName,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }

            Card(
                modifier = Modifier
                    .widthIn(max = 280.dp)
                    .border(
                        width = if (message.isFromUser) 0.dp else 1.dp,
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomStart = if (message.isFromUser) 20.dp else 4.dp,
                            bottomEnd = if (message.isFromUser) 4.dp else 20.dp
                        )
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = if (message.isFromUser)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.surface
                ),
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp,
                    bottomStart = if (message.isFromUser) 20.dp else 4.dp,
                    bottomEnd = if (message.isFromUser) 4.dp else 20.dp
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                // Contenido del mensaje
                Text(
                    text = message.text,
                    modifier = Modifier.padding(16.dp),
                    color = if (message.isFromUser)
                        MaterialTheme.colorScheme.onPrimary
                    else
                        MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )

                // Mostrar formulario solo si no es del usuario y tiene un tipo de formulario
                if (!message.isFromUser && message.formType != null) {
                    when (message.formType) {
                        FormType.RADIO_BUTTON -> {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                thickness = 0.5.dp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            RadioButtonForm(
                                options = message.formOptions,
                                indexSelected = message.selectedIndex,
                                enableForm = message.enableForm,
                                onRadioButtonSelected = { data, idx ->
                                    onRadioButtonSelected(data, idx, message.id)
                                }
                            )
                        }
                        FormType.CHECKBOX -> {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                thickness = 0.5.dp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            CheckBoxForm(
                                options = message.formOptions,
                                selectedOptions = message.selectedOptions,
                                enableForm = message.enableForm,
                                onCheckBoxSelected = { data, idx ->
                                    onCheckBoxSelected(data, idx, message.id)
                                },
                                onConfirmCheckboxSelection = {
                                    onConfirmCheckboxSelection?.invoke(message.id)
                                }
                            )
                        }
                        FormType.TEXT_INPUT -> { /* aquí tu TextInput ya existente, usando messageText */ }
                    }
                }
            }
        }

        if (message.isFromUser) {
            Spacer(modifier = Modifier.width(12.dp))
            ChatAvatar(
                background = MaterialTheme.colorScheme.secondary,
                contentAlignment = Alignment.Center,
                text = "👤"
            )
        }
    }
}