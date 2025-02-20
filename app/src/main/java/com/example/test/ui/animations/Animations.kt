import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

object Animations {
    val buttonClickScale = Spring.DampingRatioMediumBouncy
    
    fun buttonPressAnimation() = scaleIn(
        animationSpec = spring(
            dampingRatio = buttonClickScale,
            stiffness = Spring.StiffnessLow
        )
    ) + fadeIn() with scaleOut() + fadeOut()

    fun screenTransition() = slideInHorizontally(
        initialOffsetX = { fullWidth -> fullWidth },
        animationSpec = tween(durationMillis = 300)
    ) + fadeIn() with slideOutHorizontally(
        targetOffsetX = { fullWidth -> -fullWidth },
        animationSpec = tween(durationMillis = 300)
    ) + fadeOut()
} 