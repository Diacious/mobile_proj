package com.example.myapplication.data.repository

import com.example.myapplication.data.dao.UserRegistrationDao
import com.example.myapplication.data.entities.UserRegistrationData

class UserRepository(private val dao: UserRegistrationDao) {
    suspend fun insertUserData(userData: UserRegistrationData): Long {
        return dao.insert(userData)
    }
    suspend fun getUserByEmail(email: String): UserRegistrationData? {
        return dao.getUserByEmail(email)
    }
    suspend fun updateUser(user: UserRegistrationData) {
        dao.updateUser(user)
    }
}