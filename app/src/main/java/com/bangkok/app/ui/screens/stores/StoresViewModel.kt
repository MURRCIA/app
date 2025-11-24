package com.bangkok.app.ui.screens.stores

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkok.app.data.models.MockStoreData
import com.bangkok.app.data.models.Store
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class StoresUiState(
    val stores: List<Store> = emptyList(),
    val userLocation: Location? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val hasLocationPermission: Boolean = false,
    val hasCameraPermission: Boolean = false
)

class StoresViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(StoresUiState())
    val uiState: StateFlow<StoresUiState> = _uiState.asStateFlow()
    
    init {
        loadStores()
    }
    
    private fun loadStores() {
        _uiState.value = _uiState.value.copy(
            stores = MockStoreData.stores,
            isLoading = false
        )
    }
    
    fun updateLocationPermission(hasPermission: Boolean) {
        _uiState.value = _uiState.value.copy(hasLocationPermission = hasPermission)
    }
    
    fun updateCameraPermission(hasPermission: Boolean) {
        _uiState.value = _uiState.value.copy(hasCameraPermission = hasPermission)
    }
    
    fun updateUserLocation(location: Location?) {
        _uiState.value = _uiState.value.copy(userLocation = location)
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}

