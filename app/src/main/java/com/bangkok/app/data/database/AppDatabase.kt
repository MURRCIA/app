package com.bangkok.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bangkok.app.data.database.converters.Converters
import com.bangkok.app.data.database.dao.CartItemDao
import com.bangkok.app.data.database.dao.CategoryDao
import com.bangkok.app.data.database.dao.ProductDao
import com.bangkok.app.data.database.dao.UserDao
import com.bangkok.app.data.database.entities.CartItemEntity
import com.bangkok.app.data.database.entities.CategoryEntity
import com.bangkok.app.data.database.entities.ProductEntity
import com.bangkok.app.data.database.entities.UserEntity
import com.bangkok.app.data.database.entities.toEntity
import com.bangkok.app.data.models.MockCategoryData
import com.bangkok.app.data.models.MockProductData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        ProductEntity::class,
        CategoryEntity::class,
        UserEntity::class,
        CartItemEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
    abstract fun userDao(): UserDao
    abstract fun cartItemDao(): CartItemDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bangkok_database"
                )
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
    
    // Callback que se ejecuta cuando se crea la base de datos por primera vez
    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database)
                }
            }
        }
    }
    
    // Función para poblar datos iniciales
    private suspend fun populateDatabase(database: AppDatabase) {
        val productDao = database.productDao()
        val categoryDao = database.categoryDao()
        
        // Verificar si ya hay datos (evitar duplicados)
        val productCount = productDao.getProductCount()
        val categoryCount = categoryDao.getCategoryCount()
        
        if (productCount == 0 && categoryCount == 0) {
            // 1. Insertar categorías desde MockCategoryData
            val categories = MockCategoryData.categories.map { category ->
                category.toEntity()
            }
            categoryDao.insertCategories(categories)
            
            // 2. Insertar productos desde MockProductData
            val products = MockProductData.sampleProducts.map { product ->
                product.toEntity()
            }
            productDao.insertProducts(products)
        }
    }
}

