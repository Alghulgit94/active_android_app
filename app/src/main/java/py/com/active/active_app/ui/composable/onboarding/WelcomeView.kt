package py.com.active.active_app.ui.composable.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import py.com.active.active_app.ui.composable.common.GradientText
import py.com.active.active_app.ui.composable.onboarding.compose.AnimatedDotsIndicator
import py.com.active.active_app.ui.composable.onboarding.compose.OnboardingFooter
import py.com.active.active_app.ui.composable.onboarding.compose.steps.OnboardingStep1View
import py.com.active.active_app.ui.theme.ActiveAppExtendedColors
import py.com.active.active_app.ui.theme.ActiveAppTheme
import py.com.active.active_app.ui.theme.PrimaryGradient

// Screen 2: Welcome Screen (Refactorizada)
@Composable
fun WelcomeView(
    modifier: Modifier,
    state: OnboardingUiState,
    onNameChanged: (String) -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Circular Progress/Logo Area
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(ActiveAppExtendedColors.primaryGradient)
                .padding(4.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            // Inner circle can contain an icon or remain empty
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Welcome Text
        GradientText(
            text = "¡Hola!\uD83D\uDC4B",
            paddingBottom = 24.dp,
            gradient = PrimaryGradient
        )

        Text(
            text = "Tu camino smart-fitness empieza aquí",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 0.dp, bottom = 80.dp)
        )

        Column(Modifier.padding(16.dp)) {
            when (state.currentStep) {
                1 -> OnboardingStep1View(
                    state,
                    onNameChange = onNameChanged
                )
                /*2 -> Step2_AgeSex(onAgeChange = viewModel::updateAge, onSexChange = viewModel::updateSex)
                3 -> Step3_Measures(
                    onHeightChange = viewModel::updateHeight,
                    onWeightChange = viewModel::updateWeight,
                    onBodyFatChange = viewModel::updateBodyFat,
                    onPhotoPick = { *//* implementa foto si quieres *//* }
                )
                4 -> Step4_Objectives(
                    onLevelChange = viewModel::updateLevel,
                    onObjectiveChange = viewModel::updateObjective,
                    onExperienceChange = viewModel::updateExperience,
                    onRestrictionsChange = viewModel::updateRestrictions,
                    onInjuriesChange = viewModel::updateInjuries
                )
                5 -> Step5_Schedule(
                    onPreferredTimeChange = viewModel::updatePreferredTime,
                    onAvailabilityChange = viewModel::updateAvailability,
                    onEquipmentChange = viewModel::updateEquipment
                )*/
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        AnimatedDotsIndicator(
            currentStep = state.currentStep,
            totalSteps  = state.totalSteps,
            modifier    = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        OnboardingFooter(
            currentStep = state.currentStep,
            totalSteps = state.totalSteps,
            onBack = onPrevious,
            onNext = onNext,
            onFinish = {}
        )
    }
}

@Preview(name = "Welcome Light", showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    ActiveAppTheme(darkTheme = false) {
        val fakestate = remember {
            OnboardingUiState()
        }
        WelcomeView(modifier = Modifier, state = fakestate, {} ,{}, {})
    }
}

@Preview(name = "Welcome Dark", showBackground = true)
@Composable
fun WelcomeScreenDarkPreview() {
    ActiveAppTheme(darkTheme = true) {
        val fakestate = remember {
            OnboardingUiState()
        }
        WelcomeView(modifier = Modifier, state = fakestate, {},{}, {})
    }
}