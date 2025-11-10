package com.bangkok.app.data.database.converters

import androidx.room.TypeConverter
import com.bangkok.app.data.models.ProductCategory
import com.bangkok.app.data.models.UserPreferences
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

