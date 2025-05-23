package com.example.myapplication.ui.settings

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySettingsBinding
import com.example.myapplication.ui.main.MainActivity
import com.example.myapplication.ui.profile.ProfileActivity
//import com.example.myapplication.ui.register.MyBookingsActivity
//import com.example.myapplication.ui.register.ConnectCarActivity
import com.google.android.material.snackbar.Snackbar
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.data.db.AppDatabase
import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.net.Uri
import com.example.myapplication.ui.bookings.MyBookingsActivity
import com.example.myapplication.ui.favorite.FavoriteActivity

/**
 * SettingsActivity - экран настроек пользователя.
 * Отображает профиль (аватар, имя, email) и список пунктов меню настроек.
 */
class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Загружаем данные профиля (в реальном приложении данные берутся из SharedPreferences или базы данных)
        loadUserProfile()

        setupClickListeners()
    }

    private fun loadUserProfile() {
        // Получаем email текущего пользователя из SharedPreferences или другого источника
        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val userEmail = prefs.getString("user_email", null)

        if (userEmail != null) {
            lifecycleScope.launch {
                val dao = AppDatabase.getDatabase(applicationContext).userRegistrationDao()
                val userRepository = UserRepository(dao)
                // Запускаем запрос в IO-потоке
                val userData = withContext(Dispatchers.IO) {
                    userRepository.getUserByEmail(userEmail)
                }
                if (userData != null) {
                    // Обновляем UI
                    binding.tvUserName.text = "${userData.firstName} ${userData.lastName}"
                    binding.tvUserEmail.text = userData.email
                    // Если URI фото не пустой, устанавливаем его в ImageView
                    userData.profilePhotoUri?.let { photoUriString ->
                        val photoUri = Uri.parse(photoUriString)
                        binding.ivAvatar.setImageURI(photoUri)
                    }
                } else {
                    binding.tvUserName.text = "Пользователь не найден"
                    binding.tvUserEmail.text = ""
                }
            }
        } else {
            binding.tvUserName.text = "Пользователь не найден"
            binding.tvUserEmail.text = ""
        }
    }

    private fun setupClickListeners() {
        // При нажатии на блок профиля переходим на экран "Профиль"
        binding.llProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Переход "Мои бронирования"
        binding.tvMyBookings.setOnClickListener {
            // TODO: Релизовать переход на мои "Мои бронирования" (когда создам сам экран)
            //startActivity(Intent(this, MyBookingsActivity::class.java))
            //Snackbar.make(binding.root, "Мои бронирования", Snackbar.LENGTH_SHORT).show()
            val intent = Intent(this, MyBookingsActivity::class.java)
            startActivity(intent)
        }

        // Пункт "Тема" — здесь можно открыть диалог выбора темы или отдельный экран
        binding.tvTheme.setOnClickListener {
            Snackbar.make(binding.root, "Выбор темы", Snackbar.LENGTH_SHORT).show()
        }

        // Пункт "Уведомления" — открытие настроек уведомлений
        binding.tvNotifications.setOnClickListener {
            Snackbar.make(binding.root, "Настройки уведомлений", Snackbar.LENGTH_SHORT).show()
        }

        // Переход "Подключить свой автомобиль"
        binding.tvConnectCar.setOnClickListener {
            // TODO: Релизовать переход на "Подключить свой автомобиль" (когда создам сам экран)
            //startActivity(Intent(this, ConnectCarActivity::class.java))
            Snackbar.make(binding.root, "Подключить свой автомобиль", Snackbar.LENGTH_SHORT).show()
        }

        // Пункт "Помощь"
        binding.tvHelp.setOnClickListener {
            Snackbar.make(binding.root, "Помощь", Snackbar.LENGTH_SHORT).show()
        }

        // Пункт "Пригласи друга"
        binding.tvInviteFriend.setOnClickListener {
            Snackbar.make(binding.root, "Пригласи друга", Snackbar.LENGTH_SHORT).show()
        }

        // Нижняя навигационная панель:
        binding.btnHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.btnFavorites.setOnClickListener {
            // Переход на экран избранное
            startActivity(Intent(this, FavoriteActivity::class.java))
            finish()
            //Snackbar.make(binding.root, "Избранное", Snackbar.LENGTH_SHORT).show()
        }
        binding.btnSettings.setOnClickListener {
            // Так как мы уже на экране настроек, можно оставить пустым или просто показать Snackbar.
            Snackbar.make(binding.root, "Уже на экране настроек", Snackbar.LENGTH_SHORT).show()
        }
    }
}
