@Composable
fun LoadingState(isLoading: Boolean) {
    AnimatedVisibility(
        visible = isLoading,
        enter = fadeIn() + scaleIn(),
        exit = fadeOut() + scaleOut()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
} 