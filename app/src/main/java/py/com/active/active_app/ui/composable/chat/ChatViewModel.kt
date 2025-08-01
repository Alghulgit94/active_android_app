
package py.com.active.active_app.ui.composable.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import py.com.active.active_app.data.PreviousInjuryEntity
import py.com.active.active_app.data.WorkoutDataRequestEntity

class ChatViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    private val _workoutRequest = MutableStateFlow(WorkoutDataRequestEntity())
    val workoutRequest: StateFlow<WorkoutDataRequestEntity> = _workoutRequest

    init {
        _uiState.update {
            it.copy(
                quickActions = getDefaultQuickActions()
            )
        }
    }

    private fun askNextStep(stepIndex: Int) {
        if (stepIndex >= _uiState.value.steps.size) {
            // fin del flujo: habilita el botón enviar
            _uiState.update { it.copy(isFlowFinished = true) }
            return
        }
        val step = _uiState.value.steps[stepIndex]
        val aiMessage = Message(
            id = generateMessageId(),
            text = step.prompt,
            isFromUser = false,
            formType = step.formType,             // null = text input
            enableForm = step.formType != null,
            formOptions = step.options
        )
        _uiState.update {
            it.copy(
                messages = it.messages + aiMessage,
                isLoading = false,
                enablePromptInput = step.formType == FormType.TEXT_INPUT,
                inputType = step.inputType ?: InputType.TEXT
            )
        }
    }

    /** Llama a este método cada vez que completes un paso del onboarding **/
    private fun updateWorkoutField(
        step: Int,
        value: Any
    ) {
        _workoutRequest.update { current ->
            when (step) {
                1 -> current.copy(level = value as String)
                2 -> current.copy(objective = value as List<String>)
                3 -> current.copy(restrictions = value as List<String>)
                4 -> current.copy(heightCm = (value as String).toIntOrNull())
                5 -> current.copy(weightKg = (value as String).toIntOrNull())
                6 -> current.copy(bodyFatPercent = (value as String).toIntOrNull())
                7 -> current.copy(weeklyAvailability = value as Map<String, List<String>>)
                8 -> current.copy(equipmentAvailable = value as List<String>)
                9 -> current.copy(previousInjuries = value as List<PreviousInjuryEntity>)
                else -> current
            }
        }
    }

    fun updateMessageText(text: String) {
        _uiState.value = _uiState.value.copy(messageText = text)
    }

    fun sendMessage() {
        val messageText = _uiState.value.messageText.trim()
        if (messageText.isBlank()) return

        val userMessage = Message(
            id = generateMessageId(),
            text = messageText,
            isFromUser = true,
            senderName = "Sophia"
        )

        _uiState.update { state ->
            state.copy(
                messages = state.messages + userMessage,
                messageText = "",
                isLoading = true,
                showPlaceholder = false,
                enablePromptInput = true,
                currentFlowStep = state.currentFlowStep + 1
            )
        }

        simulateAIResponse(_uiState.value.currentFlowStep)
    }

    fun handleQuickAction(action: QuickAction) {
        val userMessage = Message(
            id = generateMessageId(),
            text = action.title,
            isFromUser = true,
            senderName = "Sophia",
            quickActionSelected = action
        )

        _uiState.update { state ->
            state.copy(
                messages = state.messages + userMessage,
                isLoading = true,
                showPlaceholder = false,
                enablePromptInput = false,
                currentFlowStep = 1 // Empezamos el flujo en paso 1
            )
        }

        quickActionResponse(action)
    }

    private fun simulateAIResponse(formStep: Int) {
        viewModelScope.launch {
            delay(1500)
            askNextStep(formStep)
        }
    }

    private fun quickActionResponse(action: QuickAction) {
        viewModelScope.launch {
            delay(1200)
            if (action.id == "create_routine") {
                // Empezamos el flujo de preguntas
                askNextStep(0)
            }
        }
    }

    fun onRadioButtonSelected(data: String, index: Int, messageId: String) {
        // Deshabilitar el formulario del mensaje específico
        _uiState.update { state ->
            val updated = state.messages.map { msg ->
                if (msg.id == messageId) {
                    msg.copy(
                        selectedIndex = index,
                        enableForm = false,
                        isFormCompleted = true
                    )
                } else msg
            }
            state.copy(messages = updated)
        }

        viewModelScope.launch {
            delay(100)
            processFormResponse(data, messageId)
        }
    }

    fun onCheckBoxSelected(data: String, optionIndex: Int, messageId: String) {
        _uiState.update { state ->
            val updated = state.messages.map { msg ->
                if (msg.id == messageId) {
                    val selected = msg.selectedOptions.toMutableSet()
                    if (selected.contains(optionIndex)) selected.remove(optionIndex)
                    else selected.add(optionIndex)
                    msg.copy(
                        selectedOptions = selected,
                        enableForm = true, // Mantener habilitado para múltiples selecciones
                        isFormCompleted = false
                    )
                } else msg
            }
            state.copy(messages = updated)
        }
    }

    // Agrega un método para confirmar la selección de checkboxes
    fun confirmCheckboxSelection(messageId: String) {
        val state = _uiState.value
        val message = state.messages.find { it.id == messageId } ?: return

        if (message.selectedOptions.isEmpty()) return

        // 2.a) Deshabilita el formulario de ese mensaje
        _uiState.update { ui ->
            val updated = ui.messages.map { msg ->
                if (msg.id == messageId) msg.copy(enableForm = false, isFormCompleted = true)
                else msg
            }
            ui.copy(messages = updated)
        }

        // 2.b) Extrae los valores seleccionados a partir de message.formOptions
        val selectedValues = message.selectedOptions
            .sorted()  // opcional: para orden fijo
            .map { idx -> message.formOptions[idx] }

        viewModelScope.launch {
            delay(100)
            // 2.c) Pasa el List<String> directamente
            processFormResponse(selectedValues, messageId)
        }
    }

    private fun processFormResponse(selectedData: Any, messageId: String) {
        val currentStep = _uiState.value.currentFlowStep

        // 3.a) Actualiza el entity; tu método ya soporta List<String> para CHECKBOX
        updateWorkoutField(currentStep, selectedData)

        // 3.b) Crea el texto que verá el usuario
        val displayText = when (selectedData) {
            is List<*> -> selectedData.joinToString(", ")
            else -> selectedData.toString()
        }

        // 3.c) Añade el mensaje de usuario y avanza el paso
        val userMessage = Message(
            id = generateMessageId(),
            text = displayText,
            isFromUser = true
        )
        _uiState.update {
            it.copy(
                messages = it.messages + userMessage,
                isLoading = true,
                currentFlowStep = currentStep + 1
            )
        }

        viewModelScope.launch {
            delay(1_200)
            askNextStep(currentStep + 1)
        }
    }

    /** Llama a este méthod al finalizar el flujo para disparar el caso de uso **/
    fun submitWorkoutRequest() {
        viewModelScope.launch {
            val request = _workoutRequest.value
            // Aquí puedes validar que no haya nulls en campos obligatorios…
        }
    }

    private fun generateMessageId(): String =
        "msg_${System.currentTimeMillis()}_${(1000..9999).random()}"

    private fun getDefaultQuickActions(): List<QuickAction> = listOf(
        QuickAction(
            id = "create_routine",
            title = "Crear rutina",
            description = "Diseña una nueva rutina personalizada",
            icon = "✨"
        ),
        QuickAction(
            id = "today_routine",
            title = "Ver mi rutina de hoy",
            description = "Consulta tu plan de ejercicios programado",
            icon = "🏋️"
        ),
        QuickAction(
            id = "track_progress",
            title = "Registrar progreso",
            description = "Anota tus entrenamientos y logros",
            icon = "📊"
        ),
        QuickAction(
            id = "nutrition_tips",
            title = "Plan Completo",
            description = "Obtén recomendaciones alimentarias",
            icon = "🥗"
        )
    )
}
