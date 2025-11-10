package com.bangkok.app.data.database.dao

import androidx.room.*
import com.bangkok.app.data.database.entities.ProductEntity
import com.bangkok.app.data.models.ProductCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    
    @Query("SELECT * FROM products ORDER BY createdAt DESC")
    fun getAllProducts(): Flow<List<ProductEntity>>
    
    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getProductById(productId: String): ProductEntity?
    
    @Query("SELECT * FROM products WHERE category = :category ORDER BY createdAt DESC")
    fun getProductsByCategory(category: ProductCategory): Flow<List<ProductEntity>>
    
    @Query("SELECT * FROM products WHERE category = :category AND id != :excludeId ORDER BY createdAt DESC LIMIT :limit")
    fun getProductsByCategoryExcluding(category: ProductCategory, excludeId: String, limit: Int): Flow<List<ProductEntity>>
    
    @Query("SELECT * FROM products WHERE isFeatured = 1 ORDER BY createdAt DESC")
    fun getFeaturedProducts(): Flow<List<ProductEntity>>
    
    @Query("SELECT * FROM products WHERE isNewArrival = 1 ORDER BY createdAt DESC")
    fun getNewArrivals(): Flow<List<ProductEntity>>
    
    @Query("SELECT * FROM products WHERE name LIKE :query OR description LIKE :query")
    fun searchProducts(query: String): Flow<List<ProductEntity>>
    
    @Query("SELECT * FROM products WHERE discountPercentage IS NOT NULL AND discountPercentage > 0")
    fun getProductsOnSale(): Flow<List<ProductEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)
    
    @Update
    suspend fun updateProduct(product: ProductEntity)
    
    @Delete
    suspend fun deleteProduct(product: ProductEntity)
    
    @Query("DELETE FROM products WHERE id = :productId")
    suspend fun deleteProductById(productId: String)
    
    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()
    
    @Query("SELECT COUNT(*) FROM products WHERE category = :category")
    suspend fun getProductCountByCategory(category: ProductCategory): Int
    
    @Query("SELECT COUNT(*) FROM products")
    suspend fun getProductCount(): Int
}

