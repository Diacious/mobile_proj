package com.example.myapplication.ui.login

import com.example.myapplication.data.AuthRepository
import kotlinx.coroutines.*

/**
 * LoginViewModel - отвечает за логику авторизации.
 * Здесь реализована симуляция запроса на сервер для авторизации.
 */
class LoginViewModel(private val authRepository: AuthRepository) {

    // Можно использовать CoroutineScope для асинхронных операций
    private val viewModelScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    /**
     * Симулирует процесс входа.
     * @param email электронная почта
     * @param password пароль
     * @param callback результат авторизации: success, токен и сообщение об ошибке
     */
    fun login(email: String, password: String, callback: (Boolean, String?, String?) -> Unit) {
        viewModelScope.launch {
            // Симуляция задержки запроса к серверу
            delay(1500)
            // Простейшая логика: если пароль равен "123456", считаем авторизацию успешной
            if (password == "123456") {
                callback(true, "fake_token_123456", null)
            } else {
                callback(false, null, "Неверные учетные данные")
            }
        }
    }

    /**
     * Симулирует вход через Google.
     */
    fun googleLogin(callback: (Boolean, String?, String?) -> Unit) {
        viewModelScope.launch {
            // Симуляция задержки запроса
            delay(1500)
            // Симуляция успешного входа через Google
            callback(true, "google_fake_token_abcdef", null)
        }
    }

    fun onCleared() {
        viewModelScope.cancel()
    }
}
