package com.bangkok.app.data.database.dao

import androidx.room.*
import com.bangkok.app.data.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>
    
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): UserEntity?
    
    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?
    
    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun loginUser(email: String, password: String): UserEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)
    
    @Update
    suspend fun updateUser(user: UserEntity)
    
    @Delete
    suspend fun deleteUser(user: UserEntity)
    
    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUserById(userId: String)
    
    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
    
    @Query("SELECT COUNT(*) FROM users WHERE email = :email")
    suspend fun getUserCountByEmail(email: String): Int
}

