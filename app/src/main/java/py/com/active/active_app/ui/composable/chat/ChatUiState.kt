package py.com.active.active_app.ui.composable.chat

import py.com.active.active_app.data.WorkoutDataRequestEntity

data class ChatUiState(
    val workoutDataRequest: WorkoutDataRequestEntity? = null,
    val messages: List<Message> = emptyList(),
    val messageText: String = "",
    val enablePromptInput: Boolean = true,
    val isLoading: Boolean = false,
    val showPlaceholder: Boolean = true,
    val quickActions: List<QuickAction> = emptyList(),
    val quickActionSelected: QuickAction? = QuickAction(
        id = "create_routine",
        title = "Crear rutina",
        description = "Diseña una nueva rutina personalizada",
        icon = "✨"
    ),
    val currentFlowStep: Int = 0,
    val isFlowFinished: Boolean = false,
    val inputType: InputType = InputType.TEXT,
    val steps: List<ChatStep> = mutableListOf(
        ChatStep(
            prompt = "¿Cuál es tu nivel de entrenamiento?",
            formType = FormType.RADIO_BUTTON,
            options = mutableListOf("Principiante", "Intermedio", "Avanzado")
        ),
        ChatStep(
            prompt = "¿Cuál es tu objetivo principal?",
            formType = FormType.RADIO_BUTTON,
            options = mutableListOf("Perder grasa", "Ganar músculo", "Mejorar resistencia")
        ),
        ChatStep(
            prompt = "¿Tienes alguna restricción o lesión que deba saber?",
            formType = FormType.CHECKBOX,
            options = mutableListOf("Dolor de rodilla", "Molestia lumbar", "Ninguna")
        ),
        ChatStep(
            prompt = "¿Cuál es tu altura en cm?",
            formType = FormType.TEXT_INPUT,
            inputType = InputType.NUMERIC
        ),
        ChatStep(
            prompt = "¿Cuál es tu peso en kg?",
            formType = FormType.TEXT_INPUT,
            inputType = InputType.NUMERIC
        )
    )
)

// Primero, actualiza tu data class Message para manejar múltiples selecciones
data class Message(
    val id: String,
    val text: String,
    val isFromUser: Boolean,
    val senderName: String? = null,
    val quickActionSelected: QuickAction? = null,
    val formType: FormType? = null,
    val enableForm: Boolean = false,
    val isFormCompleted: Boolean = false,
    val selectedIndex: Int = -1, // Para radio buttons
    val selectedOptions: Set<Int> = emptySet(),
    val formOptions: List<String> = emptyList()
)

data class QuickAction(
    val id: String,
    val title: String,
    val description: String,
    val icon: String // You can use this for icon names or emojis
)

data class ChatStep(
    val step: Int = 0,
    val prompt: String,
    val formType: FormType?,
    val inputType: InputType? = null,
    val options: List<String> = emptyList()
)

enum class FormType { RADIO_BUTTON, CHECKBOX, TEXT_INPUT }
enum class InputType { TEXT, NUMERIC }
