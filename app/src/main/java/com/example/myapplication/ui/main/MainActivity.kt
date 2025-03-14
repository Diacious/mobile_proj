package com.example.myapplication.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

/**
 * MainActivity - главный экран приложения.
 * Отображает приветственное сообщение.
 */
class MainActivity : AppCompatActivity() {

    // Используем ViewBinding для обращения к элементам разметки
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Здесь можно реализовать дополнительную логику главного экрана
        binding.tvWelcome.text = "Добро пожаловать на главный экран!"
    }
}
