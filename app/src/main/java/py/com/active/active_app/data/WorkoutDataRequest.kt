package py.com.active.active_app.data

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutDataRequestEntity(
    val userData: UserDataEntity? = null,
    val age: Int? = null,
    val sex: String? = null,
    val level: String? = null,
    val objective: List<String>? = null,
    val restrictions: List<String>? = null,
    val heightCm: Int? = null,
    val weightKg: Int? = null,
    val bodyFatPercent: Int? = null,
    val fitnessExperienceYears: Int? = null,
    val preferredWorkoutTime: String? = null,
    val weeklyAvailability: Map<String, List<String>>? = null,
    val equipmentAvailable: List<String>? = null,
    val previousInjuries: List<PreviousInjuryEntity>? = null,
    val dietaryPreferences: String? = null,
    val timezone: String? = null
)

@Serializable
data class UserDataEntity(
    val userId: Int? = null,
    val name: String? = null
)

@Serializable
data class PreviousInjuryEntity(
    val area: String? = null,
    val year: Int? = null,
    val detail: String? = null
)
