package com.bangkok.app.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bangkok.app.data.database.converters.Converters

@Entity(tableName = "users")
@TypeConverters(Converters::class)
data class UserEntity(
    @PrimaryKey
    val id: String,
    val fullName: String,
    val email: String,
    val password: String,
    val phone: String,
    val profileImageUrl: String? = null,
    val registrationDate: String,
    val isEmailVerified: Boolean = false,
    val preferences: com.bangkok.app.data.models.UserPreferences = com.bangkok.app.data.models.UserPreferences(),
    val createdAt: Long = System.currentTimeMillis()
)

// Función de extensión para convertir Entity → Model
fun UserEntity.toUser(): com.bangkok.app.data.models.User {
    return com.bangkok.app.data.models.User(
        id = id,
        fullName = fullName,
        email = email,
        password = password,
        phone = phone,
        profileImageUrl = profileImageUrl,
        registrationDate = registrationDate,
        isEmailVerified = isEmailVerified,
        preferences = preferences
    )
}

// Función de extensión para convertir Model → Entity
fun com.bangkok.app.data.models.User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        fullName = fullName,
        email = email,
        password = password,
        phone = phone,
        profileImageUrl = profileImageUrl,
        registrationDate = registrationDate,
        isEmailVerified = isEmailVerified,
        preferences = preferences
    )
}

