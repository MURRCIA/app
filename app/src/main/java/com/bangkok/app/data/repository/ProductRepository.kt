package com.bangkok.app.data.repository

import com.bangkok.app.data.database.dao.ProductDao
import com.bangkok.app.data.database.entities.toProduct
import com.bangkok.app.data.models.Product
import com.bangkok.app.data.models.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepository(private val productDao: ProductDao) {
    
    fun getAllProducts(): Flow<List<Product>> {
        return productDao.getAllProducts().map { entities ->
            entities.map { it.toProduct() }
        }
    }
    
    suspend fun getProductById(productId: String): Product? {
        return productDao.getProductById(productId)?.toProduct()
    }
    
    fun getProductsByCategory(category: ProductCategory): Flow<List<Product>> {
        return productDao.getProductsByCategory(category).map { entities ->
            entities.map { it.toProduct() }
        }
    }
    
    fun getFeaturedProducts(): Flow<List<Product>> {
        return productDao.getFeaturedProducts().map { entities ->
            entities.map { it.toProduct() }
        }
    }
    
    fun getNewArrivals(): Flow<List<Product>> {
        return productDao.getNewArrivals().map { entities ->
            entities.map { it.toProduct() }
        }
    }
    
    fun searchProducts(query: String): Flow<List<Product>> {
        return productDao.searchProducts("%$query%").map { entities ->
            entities.map { it.toProduct() }
        }
    }
    
    fun getProductsOnSale(): Flow<List<Product>> {
        return productDao.getProductsOnSale().map { entities ->
            entities.map { it.toProduct() }
        }
    }
    
    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product.toEntity())
    }
    
    suspend fun insertProducts(products: List<Product>) {
        productDao.insertProducts(products.map { it.toEntity() })
    }
    
    suspend fun updateProduct(product: Product) {
        productDao.updateProduct(product.toEntity())
    }
    
    suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product.toEntity())
    }
    
    suspend fun deleteProductById(productId: String) {
        productDao.deleteProductById(productId)
    }
}

