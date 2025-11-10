package com.bangkok.app.data.database.converters

import androidx.room.TypeConverter
import com.bangkok.app.data.models.ProductCategory
import com.bangkok.app.data.models.ProductSize
import com.bangkok.app.data.models.UserPreferences
import com.bangkok.app.data.models.UserRole
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    // Converter para ProductCategory enum
    @TypeConverter
    fun fromProductCategory(category: ProductCategory): String {
        return category.name
    }

    @TypeConverter
    fun toProductCategory(category: String): ProductCategory {
        return ProductCategory.valueOf(category)
    }

    // Converter para UserRole enum
    @TypeConverter
    fun fromUserRole(role: UserRole): String {
        return role.name
    }

    @TypeConverter
    fun toUserRole(role: String): UserRole {
        return UserRole.valueOf(role)
    }

    // Converter para List<String> (tags)
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        if (value.isEmpty()) return emptyList()
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType) ?: emptyList()
    }

    // Converter para List<ProductSize>
    @TypeConverter
    fun fromProductSizeList(value: List<ProductSize>): String {
        val sizeNames = value.map { it.name }
        return gson.toJson(sizeNames)
    }

    @TypeConverter
    fun toProductSizeList(value: String): List<ProductSize> {
        if (value.isEmpty()) return emptyList()
        val listType = object : TypeToken<List<String>>() {}.type
        val sizeNames: List<String> = gson.fromJson(value, listType) ?: return emptyList()
        return sizeNames.mapNotNull { 
            try {
                ProductSize.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }

    // Converter para UserPreferences
    @TypeConverter
    fun fromUserPreferences(preferences: UserPreferences): String {
        return gson.toJson(preferences)
    }

    @TypeConverter
    fun toUserPreferences(value: String): UserPreferences {
        if (value.isEmpty()) return UserPreferences()
        return gson.fromJson(value, UserPreferences::class.java) ?: UserPreferences()
    }
}

