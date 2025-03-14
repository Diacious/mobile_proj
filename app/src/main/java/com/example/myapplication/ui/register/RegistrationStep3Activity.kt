package com.example.myapplication.ui.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.example.myapplication.R

/**
 * RegistrationStep3Activity — экран для загрузки документов и фото пользователя.
 */
class RegistrationStep3Activity : AppCompatActivity() {

    private lateinit var ivProfilePicture: ImageView
    private lateinit var etLicenseNumber: EditText
    private lateinit var etIssueDate: EditText
    private lateinit var btnUploadLicense: Button
    private lateinit var btnUploadPassport: Button
    private lateinit var btnNext: Button
    private lateinit var btnBack: Button

    // Флаги, показывающие, что фото успешно выбраны (лицевой и паспорта)
    private var isLicensePhotoUploaded = false
    private var isPassportPhotoUploaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_step3)

        // Инициализация View
        ivProfilePicture = findViewById(R.id.ivProfilePicture)
        etLicenseNumber = findViewById(R.id.etLicenseNumber)
        etIssueDate = findViewById(R.id.etIssueDate)
        btnUploadLicense = findViewById(R.id.btnUploadLicense)
        btnUploadPassport = findViewById(R.id.btnUploadPassport)
        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)

        // Изначально обновляем состояние кнопки "Далее"
        updateNextButtonState()

        // Слушатели изменений для обязательных полей
        etLicenseNumber.addTextChangedListener(textWatcher)
        etIssueDate.addTextChangedListener(textWatcher)

        // Обработка нажатия на иконку фото профиля (опционально)
        ivProfilePicture.setOnClickListener {
            // TODO: Реализуйте открытие галереи или камеры для выбора фото профиля.
        }

        // Обработка нажатия на кнопку "Загрузить фото" для водительского удостоверения
        btnUploadLicense.setOnClickListener {
            // TODO: Реализуйте открытие галереи или камеры для загрузки фото водительского удостоверения.
            // После успешного выбора фото:
            isLicensePhotoUploaded = true
            updateNextButtonState()
        }

        // Обработка нажатия на кнопку "Загрузить фото" для паспорта
        btnUploadPassport.setOnClickListener {
            // TODO: Реализуйте открытие галереи или камеры для загрузки фото паспорта.
            // После успешного выбора фото:
            isPassportPhotoUploaded = true
            updateNextButtonState()
        }

        // Обработка нажатия кнопки "Далее"
        btnNext.setOnClickListener {
            if (validateInput()) {
                // Если данные валидны, завершаем регистрацию и переходим на экран "Успешная регистрация"
                val intent = Intent(this, SuccessActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Обработка нажатия кнопки "Назад"
        btnBack.setOnClickListener {
            // Возврат на предыдущий экран регистрации (шаг 2)
            finish()
        }
    }

    // Слушатель изменений текста для обновления состояния кнопки "Далее"
    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            updateNextButtonState()
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    // Обновление состояния кнопки "Далее"
    private fun updateNextButtonState() {
        btnNext.isEnabled = areRequiredFieldsFilled() && isLicensePhotoUploaded && isPassportPhotoUploaded
    }

    // Проверка, что обязательные поля заполнены
    private fun areRequiredFieldsFilled(): Boolean {
        val licenseNumber = etLicenseNumber.text.toString().trim()
        val issueDate = etIssueDate.text.toString().trim()
        return licenseNumber.isNotEmpty() && issueDate.isNotEmpty()
    }

    // Валидация введённых данных
    private fun validateInput(): Boolean {
        val licenseNumber = etLicenseNumber.text.toString().trim()
        val issueDate = etIssueDate.text.toString().trim()

        // Проверка заполненности обязательных полей и загрузки фото
        if (licenseNumber.isEmpty() || issueDate.isEmpty() || !isLicensePhotoUploaded || !isPassportPhotoUploaded) {
            Snackbar.make(findViewById(android.R.id.content),
                "Пожалуйста, заполните все обязательные поля.",
                Snackbar.LENGTH_SHORT).show()
            return false
        }

        // Проверка корректности формата даты выдачи (DD/MM/YYYY)
        val datePattern = Regex("^(0[1-9]|[12]\\d|3[01])/(0[1-9]|1[0-2])/\\d{4}$")
        if (!datePattern.matches(issueDate)) {
            Snackbar.make(findViewById(android.R.id.content),
                "Введите корректную дату выдачи.",
                Snackbar.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}