package com.example.myapplication.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySuccessBinding
import com.example.myapplication.ui.main.MainActivity
import com.example.myapplication.data.AuthRepository
import com.example.myapplication.data.entities.UserRegistrationData

/**
 * SuccessActivity — экран поздравления после успешной регистрации.
 */
class SuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessBinding
    private lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Инициализация репозитория авторизации с передачей контекста
        authRepository = AuthRepository(applicationContext)

        // Обработка нажатия кнопки "Далее"
        binding.btnNext.setOnClickListener {
            val email = intent.extras?.getString("email") ?: "dima@mail.ru"
            authRepository.saveEmail(email)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
