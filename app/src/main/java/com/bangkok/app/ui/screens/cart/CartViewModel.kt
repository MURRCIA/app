package com.bangkok.app.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.bangkok.app.data.repository.CartRepository
import com.bangkok.app.data.SessionManager
import com.bangkok.app.data.models.CartItem

data class CartUiState(
    val cartItems: List<CartItem> = emptyList(),
    val total: Double = 0.0,
    val itemCount: Int = 0,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class CartViewModel(
    private val cartRepository: CartRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    
    private fun getUserId(): String? = sessionManager.getUserId()
    
    val cartItems: StateFlow<List<CartItem>> = run {
        val currentUserId = getUserId()
        if (currentUserId != null) {
            cartRepository.getCartItems(currentUserId)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = emptyList()
                )
        } else {
            kotlinx.coroutines.flow.MutableStateFlow(emptyList<CartItem>())
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = emptyList()
                )
        }
    }
    
    val total: StateFlow<Double> = run {
        val currentUserId = getUserId()
        if (currentUserId != null) {
            cartRepository.getCartTotal(currentUserId)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = 0.0
                )
        } else {
            kotlinx.coroutines.flow.MutableStateFlow(0.0)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = 0.0
                )
        }
    }
    
    fun addToCart(productId: String, quantity: Int = 1) {
        val currentUserId = getUserId() ?: return
        viewModelScope.launch {
            cartRepository.addToCart(currentUserId, productId, quantity)
        }
    }
    
    fun updateQuantity(cartItemId: String, quantity: Int) {
        viewModelScope.launch {
            cartRepository.updateCartItemQuantity(cartItemId, quantity)
        }
    }
    
    fun removeFromCart(cartItemId: String) {
        viewModelScope.launch {
            cartRepository.removeFromCart(cartItemId)
        }
    }
    
    fun clearCart() {
        val currentUserId = getUserId() ?: return
        viewModelScope.launch {
            cartRepository.clearCart(currentUserId)
        }
    }
}

