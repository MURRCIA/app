package com.bangkok.app.data.repository

import com.bangkok.app.data.database.dao.CartItemDao
import com.bangkok.app.data.database.dao.ProductDao
import com.bangkok.app.data.database.entities.CartItemEntity
import com.bangkok.app.data.models.CartItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.combine
import java.util.UUID

class CartRepository(
    private val cartItemDao: CartItemDao,
    private val productDao: ProductDao
) {
    
    fun getCartItems(userId: String): Flow<List<CartItem>> {
        // Combinar el flujo de cartItems con el flujo de todos los productos
        // y hacer el join en memoria
        return combine(
            cartItemDao.getCartItemsByUser(userId),
            productDao.getAllProducts()
        ) { cartItems, allProducts ->
            val productsMap = allProducts.associateBy { it.id }
            cartItems.mapNotNull { cartItem ->
                productsMap[cartItem.productId]?.let { productEntity ->
                    CartItem(
                        id = cartItem.id,
                        userId = cartItem.userId,
                        product = productEntity.toProduct(),
                        quantity = cartItem.quantity,
                        addedAt = cartItem.addedAt
                    )
                }
            }
        }
    }
    
    suspend fun addToCart(userId: String, productId: String, quantity: Int = 1): Result<Unit> {
        return try {
            val existingItem = cartItemDao.getCartItem(userId, productId)
            if (existingItem != null) {
                // Si ya existe, actualizar la cantidad
                val updatedItem = existingItem.copy(quantity = existingItem.quantity + quantity)
                cartItemDao.updateCartItem(updatedItem)
            } else {
                // Si no existe, crear nuevo item
                val cartItem = CartItemEntity(
                    id = UUID.randomUUID().toString(),
                    userId = userId,
                    productId = productId,
                    quantity = quantity
                )
                cartItemDao.insertCartItem(cartItem)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateCartItemQuantity(cartItemId: String, quantity: Int): Result<Unit> {
        return try {
            val cartItem = cartItemDao.getCartItemById(cartItemId)
            if (cartItem != null) {
                if (quantity <= 0) {
                    cartItemDao.deleteCartItemById(cartItemId)
                } else {
                    val updatedItem = cartItem.copy(quantity = quantity)
                    cartItemDao.updateCartItem(updatedItem)
                }
                Result.success(Unit)
            } else {
                Result.failure(Exception("Item del carrito no encontrado"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun removeFromCart(cartItemId: String): Result<Unit> {
        return try {
            cartItemDao.deleteCartItemById(cartItemId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun removeFromCart(userId: String, productId: String): Result<Unit> {
        return try {
            cartItemDao.deleteCartItemByUserAndProduct(userId, productId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun clearCart(userId: String): Result<Unit> {
        return try {
            cartItemDao.deleteAllCartItemsByUser(userId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getCartItemCount(userId: String): Int {
        return cartItemDao.getCartItemCount(userId)
    }
    
    fun getCartTotal(userId: String): Flow<Double> {
        return getCartItems(userId).map { items ->
            items.sumOf { it.totalPrice }
        }
    }
}

