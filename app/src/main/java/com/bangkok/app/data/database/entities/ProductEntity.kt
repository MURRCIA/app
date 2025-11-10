package com.bangkok.app.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bangkok.app.data.database.converters.Converters
import com.bangkok.app.data.models.ProductCategory

@Entity(tableName = "products")
@TypeConverters(Converters::class)
data class ProductEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val category: ProductCategory,
    val imageUrl: String? = null,
    val tags: List<String> = emptyList(),
    val isFeatured: Boolean = false,
    val isNewArrival: Boolean = false,
    val discountPercentage: Int? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

// Función de extensión para convertir Entity → Model
fun ProductEntity.toProduct(): com.bangkok.app.data.models.Product {
    return com.bangkok.app.data.models.Product(
        id = id,
        name = name,
        description = description,
        price = price,
        category = category,
        imageUrl = imageUrl,
        tags = tags,
        isFeatured = isFeatured,
        isNewArrival = isNewArrival,
        discountPercentage = discountPercentage
    )
}

// Función de extensión para convertir Model → Entity
fun com.bangkok.app.data.models.Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        description = description,
        price = price,
        category = category,
        imageUrl = imageUrl,
        tags = tags,
        isFeatured = isFeatured,
        isNewArrival = isNewArrival,
        discountPercentage = discountPercentage
    )
}

