package com.bangkok.app.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.bangkok.app.data.models.MockUserData

data class ProfileUiState(
    val user: com.bangkok.app.data.models.User? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            // Simular carga de datos del usuario
            kotlinx.coroutines.delay(1000)
            
            // Usar datos mock por ahora
            _uiState.value = _uiState.value.copy(
                user = MockUserData.sampleUser,
                isLoading = false
            )
        }
    }

    fun logout() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            // Simular logout
            kotlinx.coroutines.delay(500)
            
            _uiState.value = _uiState.value.copy(
                user = null,
                isLoading = false
            )
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}
