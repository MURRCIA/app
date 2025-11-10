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
    val discountPercentage: Int? = null,
    val detailedDescription: String? = null,
    val availableSizes: List<ProductSize> = emptyList()
)

enum class ProductCategory {
    TSHIRT,
    HOODIE,
    JACKET,
    PANTS,
    ACCESSORIES
}

enum class ProductSize {
    XS,
    S,
    M,
    L,
    XL,
    XXL
}

// Mock data para pruebas
object MockProductData {
    private val defaultDetailedDescription = """
        Esta chaqueta bomber está confeccionada en denim grueso. Presenta una silueta clásica con cuello vuelto y puños y bajo elásticos de punto. Cuenta con bolsillos laterales y cierre de broche con el logo de la marca. Es perfecta para entretiempo. 
        
        Instrucciones de cuidado: Lavar a mano o a máquina en ciclo delicado con una temperatura máxima del agua de 40 °C. No remojar. Al lavar a máquina, centrifugar a no más de 400 rpm. No usar detergentes con cloro ni lejía. No usar secadora. Secar en plano. Planchar a una temperatura máxima de 150 °C; se puede usar vapor, evitando el contacto con los estampados. Se permite la limpieza en seco, sujeta a la disponibilidad de la tintorería, que seleccionará el método más adecuado. 
        
        El tiempo de producción es de 30 días o menos.
    """.trimIndent()
    
    private val allSizes = listOf(ProductSize.XS, ProductSize.S, ProductSize.M, ProductSize.L, ProductSize.XL, ProductSize.XXL)
    
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
            isNewArrival = true,
            detailedDescription = defaultDetailedDescription,
            availableSizes = allSizes
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
            isNewArrival = false,
            detailedDescription = defaultDetailedDescription,
            availableSizes = allSizes
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
            discountPercentage = 15,
            detailedDescription = defaultDetailedDescription,
            availableSizes = allSizes
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
            isNewArrival = true,
            detailedDescription = defaultDetailedDescription,
            availableSizes = allSizes
        )
    )
}

