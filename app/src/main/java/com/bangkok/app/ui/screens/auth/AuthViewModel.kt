package com.bangkok.app.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.bangkok.app.data.repository.UserRepository
import com.bangkok.app.data.SessionManager
import java.util.UUID
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val fullName: String = "",
    val phone: String = "",
    val confirmPassword: String = "",
    val rememberMe: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val fullNameError: String? = null,
    val phoneError: String? = null,
    val loginSuccess: Boolean = false,
    val registerSuccess: Boolean = false
)

class AuthViewModel(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            emailError = null
        )
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            passwordError = null
        )
    }

    fun updateFullName(fullName: String) {
        _uiState.value = _uiState.value.copy(
            fullName = fullName,
            fullNameError = null
        )
    }

    fun updatePhone(phone: String) {
        _uiState.value = _uiState.value.copy(
            phone = phone,
            phoneError = null
        )
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _uiState.value = _uiState.value.copy(
            confirmPassword = confirmPassword,
            confirmPasswordError = null
        )
    }

    fun updateRememberMe(rememberMe: Boolean) {
        _uiState.value = _uiState.value.copy(rememberMe = rememberMe)
    }

    fun login() {
        val currentState = _uiState.value
        
        // Validaciones básicas
        val emailError = if (currentState.email.isBlank()) {
            "El correo es requerido"
        } else if (!isValidEmail(currentState.email)) {
            "Correo inválido"
        } else null

        val passwordError = if (currentState.password.isBlank()) {
            "La contraseña es requerida"
        } else null

        if (emailError != null || passwordError != null) {
            _uiState.value = currentState.copy(
                emailError = emailError,
                passwordError = passwordError
            )
            return
        }
        
        _uiState.value = currentState.copy(isLoading = true, errorMessage = null)
        
        viewModelScope.launch {
            try {
                val user = userRepository.loginUser(currentState.email, currentState.password)
                if (user != null) {
                    // Guardar sesión
                    sessionManager.saveUserId(user.id)
                    _uiState.value = currentState.copy(
                        isLoading = false,
                        loginSuccess = true,
                        errorMessage = null
                    )
                } else {
                    _uiState.value = currentState.copy(
                        isLoading = false,
                        errorMessage = "Credenciales incorrectas",
                        loginSuccess = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = currentState.copy(
                    isLoading = false,
                    errorMessage = "Error al iniciar sesión: ${e.message}",
                    loginSuccess = false
                )
            }
        }
    }

    fun register() {
        val currentState = _uiState.value
        
        // Validaciones
        val fullNameError = if (currentState.fullName.isBlank()) {
            "El nombre es requerido"
        } else null

        val emailError = if (currentState.email.isBlank()) {
            "El correo es requerido"
        } else if (!isValidEmail(currentState.email)) {
            "Correo inválido"
        } else null

        val phoneError = if (currentState.phone.isBlank()) {
            "El teléfono es requerido"
        } else if (!isValidPhone(currentState.phone)) {
            "Teléfono inválido"
        } else null

        val passwordError = if (currentState.password.isBlank()) {
            "La contraseña es requerida"
        } else if (currentState.password.length < 6) {
            "La contraseña debe tener al menos 6 caracteres"
        } else null

        val confirmPasswordError = if (currentState.confirmPassword != currentState.password) {
            "Las contraseñas no coinciden"
        } else null

        if (fullNameError != null || emailError != null || phoneError != null || 
            passwordError != null || confirmPasswordError != null) {
            _uiState.value = currentState.copy(
                fullNameError = fullNameError,
                emailError = emailError,
                phoneError = phoneError,
                passwordError = passwordError,
                confirmPasswordError = confirmPasswordError
            )
            return
        }

        _uiState.value = currentState.copy(isLoading = true, errorMessage = null)
        
        viewModelScope.launch {
            try {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val registrationDate = dateFormat.format(Date())
                
                val newUser = com.bangkok.app.data.models.User(
                    id = UUID.randomUUID().toString(),
                    fullName = currentState.fullName,
                    email = currentState.email,
                    password = currentState.password,
                    phone = currentState.phone,
                    registrationDate = registrationDate,
                    isEmailVerified = false,
                    role = com.bangkok.app.data.models.UserRole.USER
                )
                
                val result = userRepository.registerUser(newUser)
                if (result.isSuccess) {
                    val user = result.getOrNull()!!
                    // Guardar sesión
                    sessionManager.saveUserId(user.id)
                    _uiState.value = currentState.copy(
                        isLoading = false,
                        registerSuccess = true,
                        errorMessage = null
                    )
                } else {
                    val exception = result.exceptionOrNull()
                    _uiState.value = currentState.copy(
                        isLoading = false,
                        errorMessage = exception?.message ?: "Error al crear la cuenta",
                        registerSuccess = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = currentState.copy(
                    isLoading = false,
                    errorMessage = "Error al crear la cuenta: ${e.message}",
                    registerSuccess = false
                )
            }
        }
    }

    fun clearErrors() {
        _uiState.value = _uiState.value.copy(
            errorMessage = null,
            emailError = null,
            passwordError = null,
            confirmPasswordError = null,
            fullNameError = null,
            phoneError = null
        )
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPhone(phone: String): Boolean {
        return phone.matches(Regex("^[+]?[0-9\\s\\-()]{10,}$"))
    }
}
