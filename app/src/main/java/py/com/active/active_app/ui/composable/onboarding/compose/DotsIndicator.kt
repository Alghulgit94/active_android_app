package py.com.active.active_app.ui.composable.onboarding.compose

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.getValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedDotsIndicator(
    currentStep: Int,
    totalSteps: Int,
    modifier: Modifier = Modifier,
    baseDotSize: Dp = 8.dp,
    selectedDotSize: Dp = 12.dp,
    dotSpacing: Dp = 8.dp,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
) {
    val transition = updateTransition(targetState = currentStep, label = "DotsTransition")

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dotSpacing),
        verticalAlignment = Alignment.CenterVertically  // <- aquí el centro vertical
    ) {
        for (step in 1..totalSteps) {
            val dotSize by transition.animateDp(
                transitionSpec = { spring(stiffness = Spring.StiffnessMedium) },
                label = "DotSize$step"
            ) { selectedStep ->
                if (step == selectedStep) selectedDotSize else baseDotSize
            }

            val dotColor by transition.animateColor(
                transitionSpec = { tween(durationMillis = 300) },
                label = "DotColor$step"
            ) { selectedStep ->
                if (step == selectedStep) activeColor else inactiveColor
            }

            Box(
                modifier = Modifier
                    .size(dotSize)
                    .clip(CircleShape)
                    .background(dotColor)
            )
        }
    }
}