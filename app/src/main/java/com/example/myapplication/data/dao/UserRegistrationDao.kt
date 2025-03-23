package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.entities.UserRegistrationData

@Dao
interface UserRegistrationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserRegistrationData): Long

    @Query("SELECT * FROM user_registration WHERE id = :id")
    suspend fun getUserById(id: Int): UserRegistrationData?

    @Query("SELECT * FROM user_registration WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserRegistrationData?

    @Update
    suspend fun updateUser(user: UserRegistrationData)
}