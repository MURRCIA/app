package com.bangkok.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
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
import com.bangkok.app.data.models.UserRole
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID
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
    version = 3,
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
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // Migración de versión 1 a 2: agregar columna role
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE users ADD COLUMN role TEXT NOT NULL DEFAULT 'USER'")
            }
        }

        // Migración de versión 2 a 3: agregar detailedDescription y availableSizes a products, selectedSize a cart_items
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Agregar columna detailedDescription a products
                db.execSQL("ALTER TABLE products ADD COLUMN detailedDescription TEXT")
                // Agregar columna availableSizes a products (se almacena como JSON string)
                db.execSQL("ALTER TABLE products ADD COLUMN availableSizes TEXT NOT NULL DEFAULT '[]'")
                // Agregar columna selectedSize a cart_items
                db.execSQL("ALTER TABLE cart_items ADD COLUMN selectedSize TEXT")
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
        
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            // Verificar y crear usuario admin cada vez que se abre la base de datos
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    ensureAdminUserExists(database)
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
            
            // 3. Asegurar que el usuario admin existe
            ensureAdminUserExists(database)
        }
        
        // Función para asegurar que el usuario admin existe
        private suspend fun ensureAdminUserExists(database: AppDatabase) {
            val userDao = database.userDao()
            val adminUser = userDao.getUserByEmail("admin@bangkok.com")
            
            if (adminUser == null) {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val adminUserEntity = UserEntity(
                    id = UUID.randomUUID().toString(),
                    fullName = "Administrador",
                    email = "admin@bangkok.com",
                    password = "admin123",
                    phone = "+52 55 0000 0000",
                    profileImageUrl = null,
                    registrationDate = dateFormat.format(Date()),
                    isEmailVerified = true,
                    role = UserRole.ADMIN,
                    preferences = com.bangkok.app.data.models.UserPreferences()
                )
                userDao.insertUser(adminUserEntity)
            }
        }
    }
}

