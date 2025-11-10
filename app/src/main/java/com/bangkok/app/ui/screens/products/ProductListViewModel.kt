package com.bangkok.app.ui.screens.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import com.bangkok.app.data.repository.ProductRepository
import com.bangkok.app.data.models.Product
import com.bangkok.app.data.models.ProductCategory
import java.util.UUID

data class ProductListUiState(
    val searchQuery: String = "",
    val selectedCategory: ProductCategory? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val showAddProductDialog: Boolean = false,
    val editingProduct: Product? = null
)

class ProductListViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ProductListUiState())
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()
    
    val allProducts: StateFlow<List<Product>> = productRepository.getAllProducts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    
    val filteredProducts: StateFlow<List<Product>> = combine(
        allProducts,
        _uiState
    ) { products, state ->
        var filtered = products
        
        // Filtrar por categoría
        state.selectedCategory?.let { category ->
            filtered = filtered.filter { it.category == category }
        }
        
        // Filtrar por búsqueda
        if (state.searchQuery.isNotBlank()) {
            val query = state.searchQuery.lowercase()
            filtered = filtered.filter {
                it.name.lowercase().contains(query) ||
                it.description.lowercase().contains(query)
            }
        }
        
        filtered
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    
    fun searchProducts(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }
    
    fun filterByCategory(category: ProductCategory?) {
        _uiState.value = _uiState.value.copy(selectedCategory = category)
    }
    
    fun addProduct(product: Product) {
        viewModelScope.launch {
            try {
                // Generar UUID para el ID si no tiene uno
                val productWithId = if (product.id.isBlank()) {
                    product.copy(id = UUID.randomUUID().toString())
                } else {
                    product
                }
                productRepository.insertProduct(productWithId)
                _uiState.value = _uiState.value.copy(
                    showAddProductDialog = false,
                    errorMessage = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Error al agregar producto: ${e.message}"
                )
            }
        }
    }
    
    fun updateProduct(product: Product) {
        viewModelScope.launch {
            try {
                productRepository.updateProduct(product)
                _uiState.value = _uiState.value.copy(
                    editingProduct = null,
                    errorMessage = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Error al actualizar producto: ${e.message}"
                )
            }
        }
    }
    
    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            try {
                productRepository.deleteProduct(product)
                _uiState.value = _uiState.value.copy(errorMessage = null)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Error al eliminar producto: ${e.message}"
                )
            }
        }
    }
    
    fun showAddProductDialog() {
        _uiState.value = _uiState.value.copy(showAddProductDialog = true)
    }
    
    fun hideAddProductDialog() {
        _uiState.value = _uiState.value.copy(showAddProductDialog = false)
    }
    
    fun setEditingProduct(product: Product?) {
        _uiState.value = _uiState.value.copy(
            editingProduct = product,
            showAddProductDialog = product != null
        )
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}

