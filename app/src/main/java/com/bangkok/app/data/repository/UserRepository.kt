package com.bangkok.app.data.repository

import com.bangkok.app.data.database.dao.UserDao
import com.bangkok.app.data.database.entities.toUser
import com.bangkok.app.data.database.entities.toEntity
import com.bangkok.app.data.database.entities.UserEntity
import com.bangkok.app.data.models.User
import com.bangkok.app.data.models.UserRole
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class UserRepository(private val userDao: UserDao) {
    
    fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers().map { entities ->
            entities.map { it.toUser() }
        }
    }
    
    suspend fun getUserById(userId: String): User? {
        return userDao.getUserById(userId)?.toUser()
    }
    
    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)?.toUser()
    }
    
    suspend fun loginUser(email: String, password: String): User? {
        return try {
            // Asegurar que el admin existe antes de intentar login
            ensureAdminUserExists()
            userDao.loginUser(email, password)?.toUser()
        } catch (e: Exception) {
            // Si hay un error, intentar crear el admin y luego hacer login de nuevo
            try {
                ensureAdminUserExists()
                userDao.loginUser(email, password)?.toUser()
            } catch (e2: Exception) {
                null
            }
        }
    }
    
    private suspend fun ensureAdminUserExists() {
        try {
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
        } catch (e: Exception) {
            // Silenciar errores al crear admin - la base de datos puede no estar lista aún
            // El callback de la base de datos se encargará de crearlo
        }
    }
    
    suspend fun registerUser(user: User): Result<User> {
        return try {
            // Verificar si el email ya existe
            val existingUser = userDao.getUserByEmail(user.email)
            if (existingUser != null) {
                Result.failure(Exception("El email ya está registrado"))
            } else {
                userDao.insertUser(user.toEntity())
                Result.success(user)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateUser(user: User) {
        userDao.updateUser(user.toEntity())
    }
    
    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user.toEntity())
    }
    
    suspend fun userExists(email: String): Boolean {
        return userDao.getUserCountByEmail(email) > 0
    }
    
    suspend fun isAdmin(userId: String): Boolean {
        val user = getUserById(userId)
        return user?.role == UserRole.ADMIN
    }
    
    suspend fun getUserRole(userId: String): UserRole? {
        return getUserById(userId)?.role
    }
}

