package py.com.active.active_app.ui.composable.onboarding.compose.steps

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import py.com.active.active_app.ui.composable.onboarding.OnboardingUiState

@Composable
fun OnboardingStep1View(state: OnboardingUiState, onNameChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(16.dp),
        value = state.workoutRequest.userData?.name.orEmpty(), // podrías leer del ViewModel si quieres pre-llenar
        onValueChange = { onNameChange(it) },
        placeholder = { Text("¿Cómo te llamás?") },
        singleLine = true
    )
}