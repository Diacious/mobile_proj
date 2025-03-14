package com.example.myapplication.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityAuthChoiceBinding
import com.example.myapplication.ui.login.LoginActivity
import com.example.myapplication.ui.register.RegisterActivity

/**
 * AuthChoiceActivity - экран выбора действия для входа или регистрации.
 * Отображает название, логотип и слоган, а также две кнопки:
 * "Войти" и "Зарегистрироваться". При нажатии на кнопку происходит переход
 * на соответствующий экран.
 */
class AuthChoiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthChoiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Обработка нажатия на кнопку "Войти"
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Обработка нажатия на кнопку "Зарегистрироваться"
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
