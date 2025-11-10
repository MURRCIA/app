package com.bangkok.app.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import com.bangkok.app.data.repository.ProductRepository
import com.bangkok.app.data.repository.CategoryRepository
import com.bangkok.app.data.models.Product
import com.bangkok.app.data.models.Category

data class HomeUiState(
    val products: List<Product> = emptyList(),
    val categories: List<Category> = emptyList(),
    val featuredProducts: List<Product> = emptyList(),
    val newArrivals: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class HomeViewModel(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    
    val products: StateFlow<List<Product>> = productRepository.getAllProducts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    
    val categories: StateFlow<List<Category>> = categoryRepository.getAllCategories()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    
    val featuredProducts: StateFlow<List<Product>> = productRepository.getFeaturedProducts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    
    val newArrivals: StateFlow<List<Product>> = productRepository.getNewArrivals()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}

