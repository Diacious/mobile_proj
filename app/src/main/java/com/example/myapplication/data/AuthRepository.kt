package com.example.myapplication.data

import android.content.Context
import android.content.SharedPreferences

/**
 * AuthRepository - управляет хранением токена и проверкой аутентификации.
 */
class AuthRepository(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun isUserLoggedIn(): Boolean {
        val token = prefs.getString("access_token", null)
        return token != null
    }

    fun saveToken(token: String) {
        prefs.edit().putString("access_token", token).apply()
    }

    fun clearToken() {
        prefs.edit().remove("access_token").apply()
    }
}
