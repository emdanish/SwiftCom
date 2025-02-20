import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.airbnb.lottie.compose.*

@Composable
fun SplashScreen(onSplashComplete: () -> Unit) {
    var isAnimationFinished by remember { mutableStateOf(false) }
    
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset("loading_animation.json")
    )
    
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1,
        isPlaying = !isAnimationFinished,
        speed = 1.5f,
        restartOnPlay = false
    )
    
    LaunchedEffect(progress) {
        if (progress == 1f) {
            isAnimationFinished = true
            onSplashComplete()
        }
    }
    
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress }
        )
    }
} 