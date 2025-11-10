package com.bangkok.app.data.repository

import com.bangkok.app.data.database.dao.CategoryDao
import com.bangkok.app.data.database.entities.toCategory
import com.bangkok.app.data.database.entities.toEntity
import com.bangkok.app.data.models.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepository(private val categoryDao: CategoryDao) {
    
    fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories().map { entities ->
            entities.map { it.toCategory() }
        }
    }
    
    suspend fun getCategoryById(categoryId: String): Category? {
        return categoryDao.getCategoryById(categoryId)?.toCategory()
    }
    
    suspend fun insertCategory(category: Category) {
        categoryDao.insertCategory(category.toEntity())
    }
    
    suspend fun insertCategories(categories: List<Category>) {
        categoryDao.insertCategories(categories.map { it.toEntity() })
    }
    
    suspend fun updateCategory(category: Category) {
        categoryDao.updateCategory(category.toEntity())
    }
    
    suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category.toEntity())
    }
    
    suspend fun deleteCategoryById(categoryId: String) {
        categoryDao.deleteCategoryById(categoryId)
    }
}

