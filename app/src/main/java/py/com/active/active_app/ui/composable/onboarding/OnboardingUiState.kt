package py.com.active.active_app.ui.composable.onboarding

import py.com.active.active_app.data.WorkoutDataRequestEntity

data class OnboardingUiState(
    val currentStep: Int = 1,
    val totalSteps: Int = 5,
    val workoutRequest: WorkoutDataRequestEntity = WorkoutDataRequestEntity(
        preferredWorkoutTime = "" // este campo es obligatorio en el constructor
    )
)