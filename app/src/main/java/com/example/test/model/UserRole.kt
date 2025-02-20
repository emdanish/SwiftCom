enum class UserRole {
    CUSTOMER,
    RETAILER,
    ADMIN
}

data class AuthState(
    val isFirstTime: Boolean = true,
    val isAuthenticated: Boolean = false,
    val userRole: UserRole? = null
) 