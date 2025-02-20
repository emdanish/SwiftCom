@Composable
fun RoleSelectionScreen(onRoleSelected: (UserRole) -> Unit) {
    var selectedRole by remember { mutableStateOf<UserRole?>(null) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Choose your role",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        UserRole.values().forEach { role ->
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(
                    initialOffsetY = { it * 2 }
                ) + fadeIn(),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                AnimatedButton(
                    text = role.name.lowercase().capitalize(),
                    onClick = { 
                        selectedRole = role
                        onRoleSelected(role) 
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = when(role) {
                            UserRole.CUSTOMER -> MaterialTheme.colorScheme.primary
                            UserRole.RETAILER -> MaterialTheme.colorScheme.secondary
                            UserRole.ADMIN -> MaterialTheme.colorScheme.tertiary
                        }
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
} 