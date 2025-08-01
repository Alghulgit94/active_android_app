package py.com.active.active_app.ui.composable.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import py.com.active.active_app.ui.composable.common.AIWorkoutScreenWithGradient
import py.com.active.active_app.ui.composable.common.AppBar
import py.com.active.active_app.ui.composable.common.LoadingIndicator
import py.com.active.active_app.ui.composable.common.message.MessageBubble
import py.com.active.active_app.ui.composable.common.message.MessageInput
import py.com.active.active_app.ui.composable.common.quickAction.QuickActionsSection
import py.com.active.active_app.ui.theme.ActiveAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatView(
    viewModel: ChatViewModel = viewModel(),
    state: State<ChatUiState>? = null
) {
    val uiState by viewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val listState = rememberLazyListState()

    // Auto-scroll to bottom when new messages arrive
    LaunchedEffect(uiState.messages.size) {
        if (uiState.messages.isNotEmpty()) {
            listState.animateScrollToItem(uiState.messages.size - 1)
        }
    }

    if (!uiState.isFlowFinished){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            // Top Bar
            AppBar()

            // Chat Content
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                state = listState,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Placeholder when no messages
                if (uiState.showPlaceholder) {
                    item {
                        WelcomeHeaderContent()
                    }

                    item {
                        QuickActionsSection(
                            quickActions = uiState.quickActions,
                            onQuickActionClick = viewModel::handleQuickAction
                        )
                    }
                }

                // Messages
                items(uiState.messages) { message ->
                    MessageBubble(
                        message = message,
                        onRadioButtonSelected = { data: String, index: Int, messageId: String ->
                            viewModel.onRadioButtonSelected(data, index, messageId)
                        },
                        onCheckBoxSelected = { data: String, index: Int, messageId: String ->
                            viewModel.onCheckBoxSelected(data, index, messageId)
                        },
                        onConfirmCheckboxSelection = { messageId: String ->
                            viewModel.confirmCheckboxSelection(messageId)
                        }
                    )
                }

                // Loading indicator
                if (uiState.isLoading) {
                    item {
                        LoadingIndicator()
                    }
                }

                // Bottom spacing
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            // Message Input
            /*if (!uiState.isFlowFinished) {*/
            MessageInput(
                messageText   = uiState.messageText,
                keyboardType  = uiState.inputType,
                onMessageTextChange = viewModel::updateMessageText,
                onSendMessage      = {
                    viewModel.sendMessage()
                    keyboardController?.hide()
                },
                enabled = !uiState.isLoading && uiState.enablePromptInput
            )
            /* } else {
                 // Flujo terminado: botón de envío
                 Button(
                     onClick = { viewModel.submitWorkoutRequest() },
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(16.dp)
                 ) {
                     Text("Enviar mis datos")
                 }
             }*/
        }
    }else{
        AIWorkoutScreenWithGradient()
    }
}

@Composable
private fun WelcomeHeaderContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hola, Omar",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "¿Empezamos?",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
            textAlign = TextAlign.Center,
            lineHeight = 20.sp,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}

@Preview(name = "Chat Light", showBackground = true)
@Composable
fun ImprovedChatViewPreview() {
    ActiveAppTheme(darkTheme = false) {
        ChatView(state = null)
    }
}

@Preview(name = "Chat Dark", showBackground = true)
@Composable
fun ImprovedChatViewDarkPreview() {
    ActiveAppTheme(darkTheme = true) {
        ChatView(state = null)
    }
}