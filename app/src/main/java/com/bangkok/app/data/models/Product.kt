package com.bangkok.app.data.models

import androidx.annotation.DrawableRes

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val category: ProductCategory,
    @DrawableRes val imageRes: Int? = null,
    val imageUrl: String? = null,
    val tags: List<String> = emptyList(),
    val isFeatured: Boolean = false,
    val isNewArrival: Boolean = false,
    val discountPercentage: Int? = null
)

enum class ProductCategory {
    TSHIRT,
    HOODIE,
    JACKET,
    PANTS,
    ACCESSORIES
}

// Mock data para pruebas
object MockProductData {
    val sampleProducts = listOf(
        Product(
            id = "prod_001",
            name = "Bomber Orochimaru",
            description = "Bomber jacket Orochimaru edición especial",
            price = 1299.00,
            category = ProductCategory.JACKET,
            imageUrl = "https://nikifilini.com/wp-content/webp-express/webp-images/doc-root/wp-content/uploads/2025/10/bomber-orochimaru-zad-768x1024.jpg.webp",
            tags = listOf("NUEVO", "EDICIÓN LIMITADA"),
            isFeatured = true,
            isNewArrival = true
        ),
        Product(
            id = "prod_002",
            name = "Hoodie Orochimaru",
            description = "Sudadera con capucha Orochimaru diseño exclusivo",
            price = 899.00,
            category = ProductCategory.HOODIE,
            imageUrl = "https://nikifilini.com/wp-content/webp-express/webp-images/doc-root/wp-content/uploads/2025/09/orochimaru-hud-768x1024.jpg.webp",
            tags = listOf("TRENDING", "POPULAR"),
            isFeatured = true,
            isNewArrival = false
        ),
        Product(
            id = "prod_003",
            name = "Hoodie Magichka",
            description = "Sudadera estilo urbano con diseño mágico",
            price = 849.00,
            category = ProductCategory.HOODIE,
            imageUrl = "https://nikifilini.com/wp-content/webp-express/webp-images/doc-root/wp-content/uploads/2025/09/hudi-MAGICHKA-Z-768x1024.jpg.webp",
            tags = listOf("NUEVO", "EXCLUSIVO"),
            isFeatured = true,
            isNewArrival = true,
            discountPercentage = 15
        ),
        Product(
            id = "prod_004",
            name = "Hoodie Tanjiro",
            description = "Hoodie Demon Slayer Tanjiro edición especial",
            price = 899.00,
            category = ProductCategory.HOODIE,
            imageUrl = "https://nikifilini.com/wp-content/webp-express/webp-images/doc-root/wp-content/uploads/2025/10/tandzhiro-hudi-z-768x1024.jpg.webp",
            tags = listOf("PREORDEN", "ANIME"),
            isFeatured = true,
            isNewArrival = true
        )
    )
}

