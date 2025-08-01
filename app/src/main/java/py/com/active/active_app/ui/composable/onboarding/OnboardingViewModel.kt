package py.com.active.active_app.ui.composable.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import py.com.active.active_app.data.PreviousInjuryEntity
import py.com.active.active_app.data.UserDataEntity
import py.com.active.active_app.data.WorkoutDataRequestEntity

class OnboardingViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    fun updateName(name: String) = updateField { it.copy(userData = it.userData?.copy(name = name) ?: UserDataEntity(name = name)) }
    fun updateAge(age: Int) = updateField { it.copy(age = age) }
    fun updateSex(sex: String) = updateField { it.copy(sex = sex) }
    fun updateHeight(height: Int) = updateField { it.copy(heightCm = height) }
    fun updateWeight(weight: Int) = updateField { it.copy(weightKg = weight) }
    fun updateBodyFat(percent: Int) = updateField { it.copy(bodyFatPercent = percent) }
    fun updateExperience(years: Int) = updateField { it.copy(fitnessExperienceYears = years) }
    fun updateLevel(level: String) = updateField { it.copy(level = level) }
    fun updateObjective(obj: String) = updateField { it.copy(objective = listOf(obj)) }
    fun updateRestrictions(restrictions: List<String>) = updateField { it.copy(restrictions = restrictions) }
    fun updateInjuries(injuries: List<PreviousInjuryEntity>) = updateField { it.copy(previousInjuries = injuries) }
    fun updatePreferredTime(time: String) = updateField { it.copy(preferredWorkoutTime = time) }
    fun updateAvailability(avail: Map<String, List<String>>) = updateField { it.copy(weeklyAvailability = avail) }
    fun updateEquipment(e: List<String>) = updateField { it.copy(equipmentAvailable = e) }
    fun updateDiet(pref: String) = updateField { it.copy(dietaryPreferences = pref) }
    fun updateTimezone(tz: String) = updateField { it.copy(timezone = tz) }

    private fun updateField(update: (WorkoutDataRequestEntity) -> WorkoutDataRequestEntity) {
        _uiState.update { it.copy(workoutRequest = update(it.workoutRequest)) }
    }

    fun nextStep() {
        _uiState.update {
            if (it.currentStep < it.totalSteps) it.copy(currentStep = it.currentStep + 1)
            else it
        }
    }

    fun previousStep() {
        _uiState.update {
            if (it.currentStep > 1) it.copy(currentStep = it.currentStep - 1)
            else it
        }
    }

    fun submitWorkoutRequest() {
        viewModelScope.launch {
            val request = _uiState.value.workoutRequest
            // aquí -> createWorkoutUseCase(request)
        }
    }
}