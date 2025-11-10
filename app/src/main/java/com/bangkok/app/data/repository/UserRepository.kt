package com.bangkok.app.data.repository

import com.bangkok.app.data.database.dao.UserDao
import com.bangkok.app.data.database.entities.toUser
import com.bangkok.app.data.database.entities.toEntity
import com.bangkok.app.data.models.User
import com.bangkok.app.data.models.UserRole
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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
        return userDao.loginUser(email, password)?.toUser()
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

