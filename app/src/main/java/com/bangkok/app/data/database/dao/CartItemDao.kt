package com.bangkok.app.data.database.dao

import androidx.room.*
import com.bangkok.app.data.database.entities.CartItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartItemDao {
    
    @Query("SELECT * FROM cart_items WHERE userId = :userId ORDER BY addedAt DESC")
    fun getCartItemsByUser(userId: String): Flow<List<CartItemEntity>>
    
    @Query("SELECT * FROM cart_items WHERE userId = :userId AND productId = :productId")
    suspend fun getCartItem(userId: String, productId: String): CartItemEntity?
    
    @Query("SELECT * FROM cart_items WHERE userId = :userId AND productId = :productId AND (selectedSize = :size OR (selectedSize IS NULL AND :size IS NULL))")
    suspend fun getCartItem(userId: String, productId: String, size: String?): CartItemEntity?
    
    @Query("SELECT * FROM cart_items WHERE id = :cartItemId")
    suspend fun getCartItemById(cartItemId: String): CartItemEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItemEntity)
    
    @Update
    suspend fun updateCartItem(cartItem: CartItemEntity)
    
    @Delete
    suspend fun deleteCartItem(cartItem: CartItemEntity)
    
    @Query("DELETE FROM cart_items WHERE id = :cartItemId")
    suspend fun deleteCartItemById(cartItemId: String)
    
    @Query("DELETE FROM cart_items WHERE userId = :userId")
    suspend fun deleteAllCartItemsByUser(userId: String)
    
    @Query("DELETE FROM cart_items WHERE userId = :userId AND productId = :productId")
    suspend fun deleteCartItemByUserAndProduct(userId: String, productId: String)
    
    @Query("DELETE FROM cart_items")
    suspend fun deleteAllCartItems()
    
    @Query("SELECT COUNT(*) FROM cart_items WHERE userId = :userId")
    suspend fun getCartItemCount(userId: String): Int
}

