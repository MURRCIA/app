package com.bangkok.app.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.bangkok.app.data.repository.UserRepository
import com.bangkok.app.data.SessionManager
import com.bangkok.app.data.models.UserRole

data class ProfileUiState(
    val user: com.bangkok.app.data.models.User? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class ProfileViewModel(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()
    
    private val _isAdmin = MutableStateFlow(false)
    val isAdmin: StateFlow<Boolean> = _isAdmin.asStateFlow()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                val userId = sessionManager.getUserId()
                if (userId != null) {
                    val user = userRepository.getUserById(userId)
                    val isAdminUser = userRepository.isAdmin(userId)
                    _isAdmin.value = isAdminUser
                    _uiState.value = _uiState.value.copy(
                        user = user,
                        isLoading = false,
                        errorMessage = if (user == null) "Usuario no encontrado" else null
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        user = null,
                        isLoading = false,
                        errorMessage = "No hay sesión activa"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    user = null,
                    isLoading = false,
                    errorMessage = "Error al cargar perfil: ${e.message}"
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            try {
                sessionManager.logout()
                _uiState.value = _uiState.value.copy(
                    user = null,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Error al cerrar sesión: ${e.message}"
                )
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}
