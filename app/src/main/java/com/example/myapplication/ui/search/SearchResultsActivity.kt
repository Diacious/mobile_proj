package com.example.myapplication.ui.search

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.db.AppDatabase
import com.example.myapplication.databinding.ActivitySearchResultsBinding
import com.example.myapplication.data.entities.Car
import com.example.myapplication.ui.main.MainActivity
import com.example.myapplication.ui.main.CarAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchResultsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchResultsBinding
    private lateinit var carAdapter: CarAdapter

    // Предположим, поисковый запрос передается через Intent
    private val searchQuery: String by lazy { intent.getStringExtra("search_query") ?: "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Обработчик для иконки возврата на главный экран
        binding.btnBackToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        setupRecyclerView()
        setupListeners()
        loadSearchResults()
    }

    private fun setupRecyclerView() {
        carAdapter = CarAdapter { car, action ->
            when (action) {
                CarAdapter.Action.BOOK -> {
                    // Переход на экран "Оформление аренды"
                    // Здесь добавьте нужную логику
                }
                CarAdapter.Action.DETAILS -> {
                    // Переход на экран "Детали"
                    // Здесь добавьте нужную логику
                }
            }
        }
        binding.rvSearchResults.apply {
            layoutManager = LinearLayoutManager(this@SearchResultsActivity)
            adapter = carAdapter
        }
    }

    private fun setupListeners() {
        binding.btnRetry.setOnClickListener {
            loadSearchResults()
        }
    }

    private fun loadSearchResults() {
        // Показываем индикатор загрузки и скрываем ошибки
        binding.progressBar.visibility = android.view.View.VISIBLE
        binding.layoutError.visibility = android.view.View.GONE

        lifecycleScope.launch {
            try {
                // Получение данных из базы данных
                val cars = fetchCarsFromDatabase(searchQuery)
                // Обновляем адаптер с данными
                carAdapter.submitList(cars)
            } catch (e: Exception) {
                // Обработка ошибок
                binding.layoutError.visibility = android.view.View.VISIBLE
                Snackbar.make(binding.root, "Ошибка загрузки данных. Попробуйте снова.", Snackbar.LENGTH_SHORT).show()
            } finally {
                // Скрываем индикатор загрузки
                binding.progressBar.visibility = android.view.View.GONE
            }
        }
    }

    private suspend fun fetchCarsFromDatabase(query: String): List<Car> = withContext(Dispatchers.IO) {
        // Создание или получение экземпляра базы данных
        val db = AppDatabase.getDatabase(applicationContext)
        // Выполнение запроса к базе данных
        if(query.isBlank()) {
            db.carDao().getAllCars()
        } else {
            db.carDao().searchCars(query)
        }
    }
}
