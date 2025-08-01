package py.com.active.active_app.ui.composable.onboarding.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingFooter(
    currentStep: Int,
    totalSteps: Int,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = if (currentStep > 1 && currentStep < totalSteps) {
            Arrangement.spacedBy(16.dp)
        } else {
            Arrangement.Center
        }
    ) {
        when (// Primer paso: solo "Siguiente"
            currentStep) {
            1 -> {
                Button(
                    onClick = onNext,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(63.dp)
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Siguiente",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Paso intermedio: "Atrás" + "Siguiente"
            in 2 until totalSteps -> {
                Button(
                    onClick = onBack,
                    modifier = Modifier
                        .weight(1f)
                        .height(63.dp)
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Atrás",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Button(
                    onClick = onNext,
                    modifier = Modifier
                        .weight(1f)
                        .height(63.dp)
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Siguiente",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Último paso: solo "¡Listo!"
            totalSteps -> {
                Button(
                    onClick = onFinish,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(63.dp)
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "¡Listo!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
