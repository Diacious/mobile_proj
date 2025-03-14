package com.example.myapplication.ui.splash

import android.content.Intent
import android.os.Bundle
//import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySplashBinding
import com.example.myapplication.ui.main.MainActivity
//import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.auth.AuthChoiceActivity
import com.example.myapplication.ui.onboarding.OnboardingActivity

/**
 * SplashActivity - экран загрузки приложения.
 * Отображает логотип, название и слоган.
 * Через 2-3 секунды или после проверки токена переходит на нужный экран.
 */
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel// by viewModels() Используем ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Подключаем ViewBinding
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Создаем ViewModel, передавая Context
        viewModel = ViewModelProvider(this,  SplashViewModelFactory(applicationContext))
            .get(SplashViewModel::class.java)

        viewModel.navigateTo.observe(this) { destination ->
            when (destination) {
                // Вместо Login выбираем AuthChoiceActivity
                Destination.AUTH_CHOICE -> startActivity(Intent(this, AuthChoiceActivity::class.java))
                Destination.MAIN -> startActivity(Intent(this, MainActivity::class.java))
                Destination.ONBOARDING -> startActivity(Intent(this, OnboardingActivity::class.java))
            }
            finish()
        }
        // Наблюдаем за статусом навигации
        /* viewModel.navigateTo.observe(this, Observer { destination ->
            when (destination) {
                Destination.LOGIN -> startActivity(Intent(this, LoginActivity::class.java))
                Destination.MAIN -> startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        })*/
    }
}

