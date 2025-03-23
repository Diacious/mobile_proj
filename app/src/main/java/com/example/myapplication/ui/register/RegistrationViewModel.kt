package com.example.myapplication.ui.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.db.AppDatabase
import com.example.myapplication.data.entities.UserRegistrationData
import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.launch

class RegistrationViewModel(context: Context) : ViewModel() {

    private val repository: UserRepository

    init {
        val dao = AppDatabase.getDatabase(context).userRegistrationDao()
        repository = UserRepository(dao)
    }

    fun saveRegistrationData(userData: UserRegistrationData, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                repository.insertUserData(userData)
                onComplete(true)
            } catch (e: Exception) {
                e.printStackTrace()
                onComplete(false)
            }
        }
    }
}
