package com.bangkok.app.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val imageUrl: String,
    val productCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)

// Función de extensión para convertir Entity → Model
fun CategoryEntity.toCategory(): com.bangkok.app.data.models.Category {
    return com.bangkok.app.data.models.Category(
        id = id,
        name = name,
        imageUrl = imageUrl,
        productCount = productCount
    )
}

// Función de extensión para convertir Model → Entity
fun com.bangkok.app.data.models.Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
        productCount = productCount
    )
}

