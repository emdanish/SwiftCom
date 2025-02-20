@Composable
fun AuthScreen(
    userRole: UserRole,
    onGoogleSignInClick: () -> Unit,
    onEmailSignInClick: (String, String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign in as ${userRole.name.lowercase().capitalize()}",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(vertical = 32.dp)
            )
            
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .animateContentSize()
            )
            
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .animateContentSize()
            )
            
            AnimatedButton(
                text = "Sign In with Email",
                onClick = { 
                    isLoading = true
                    onEmailSignInClick(email, password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
            
            Text(
                text = "OR",
                modifier = Modifier.padding(vertical = 16.dp)
            )
            
            AnimatedButton(
                text = "Continue with Google",
                onClick = { 
                    isLoading = true
                    onGoogleSignInClick()
                },
                colors = ButtonDefaults.outlinedButtonColors(),
                modifier = Modifier.fillMaxWidth()
            )
        }
        
        LoadingState(isLoading = isLoading)
    }
} 