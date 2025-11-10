package com.bangkok.app.data.models

data class CartItem(
    val id: String,
    val userId: String,
    val product: Product,
    val quantity: Int = 1,
    val addedAt: Long = System.currentTimeMillis()
) {
    val totalPrice: Double
        get() = product.price * quantity * (1 - (product.discountPercentage ?: 0) / 100.0)
}

