package com.bangkok.app.ui.screens.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.bangkok.app.data.repository.ProductRepository
import com.bangkok.app.data.repository.CartRepository
import com.bangkok.app.data.SessionManager
import com.bangkok.app.data.models.Product
import com.bangkok.app.data.models.ProductSize

data class ProductDetailUiState(
    val product: Product? = null,
    val selectedSize: ProductSize? = null,
    val similarProducts: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val addToCartSuccess: Boolean = false,
    val addToCartError: String? = null
)

class ProductDetailViewModel(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()
    
    private val _similarProducts = MutableStateFlow<List<Product>>(emptyList())
    val similarProducts: StateFlow<List<Product>> = _similarProducts.asStateFlow()
    
    fun loadProduct(productId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            
            try {
                val product = productRepository.getProductById(productId)
                if (product != null) {
                    _uiState.value = _uiState.value.copy(
                        product = product,
                        isLoading = false,
                        errorMessage = null
                    )
                    // Cargar productos similares automáticamente
                    loadSimilarProducts(productId)
                } else {
                    _uiState.value = _uiState.value.copy(
                        product = null,
                        isLoading = false,
                        errorMessage = "Producto no encontrado"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    product = null,
                    isLoading = false,
                    errorMessage = "Error al cargar producto: ${e.message}"
                )
            }
        }
    }
    
    fun selectSize(size: ProductSize) {
        _uiState.value = _uiState.value.copy(selectedSize = size)
    }
    
    fun addToCart() {
        val product = _uiState.value.product
        val selectedSize = _uiState.value.selectedSize
        val userId = sessionManager.getUserId()
        
        if (product == null) {
            _uiState.value = _uiState.value.copy(
                addToCartError = "Producto no disponible"
            )
            return
        }
        
        if (userId == null) {
            _uiState.value = _uiState.value.copy(
                addToCartError = "Debes iniciar sesión para agregar productos al carrito"
            )
            return
        }
        
        // Validar que se haya seleccionado una talla si el producto tiene tallas disponibles
        if (product.availableSizes.isNotEmpty() && selectedSize == null) {
            _uiState.value = _uiState.value.copy(
                addToCartError = "Por favor selecciona una talla"
            )
            return
        }
        
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                addToCartError = null,
                addToCartSuccess = false
            )
            
            try {
                val result = cartRepository.addToCart(
                    userId = userId,
                    productId = product.id,
                    quantity = 1,
                    selectedSize = selectedSize
                )
                
                if (result.isSuccess) {
                    _uiState.value = _uiState.value.copy(
                        addToCartSuccess = true,
                        addToCartError = null
                    )
                    // Limpiar el mensaje de éxito después de un tiempo
                    kotlinx.coroutines.delay(2000)
                    _uiState.value = _uiState.value.copy(addToCartSuccess = false)
                } else {
                    _uiState.value = _uiState.value.copy(
                        addToCartSuccess = false,
                        addToCartError = result.exceptionOrNull()?.message ?: "Error al agregar al carrito"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    addToCartSuccess = false,
                    addToCartError = "Error al agregar al carrito: ${e.message}"
                )
            }
        }
    }
    
    private fun loadSimilarProducts(productId: String) {
        viewModelScope.launch {
            try {
                productRepository.getSimilarProducts(productId, limit = 20)
                    .collect { products ->
                        _similarProducts.value = products
                        _uiState.value = _uiState.value.copy(similarProducts = products)
                    }
            } catch (e: Exception) {
                // Error al cargar productos similares, pero no es crítico
            }
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(
            errorMessage = null,
            addToCartError = null
        )
    }
    
    fun clearAddToCartSuccess() {
        _uiState.value = _uiState.value.copy(addToCartSuccess = false)
    }
}

