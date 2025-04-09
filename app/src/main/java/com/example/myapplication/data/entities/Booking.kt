package com.example.myapplication.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "bookings")
data class Booking(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // ID бронирования
    @ColumnInfo(name = "car_id") val carId: Long, // ID автомобиля
    @ColumnInfo(name = "user_id") val userId: Int, // ID пользователя (если необходимо)
    @ColumnInfo(name = "start_date") val startDate: Long, // Начало аренды (в формате timestamp)
    @ColumnInfo(name = "end_date") val endDate: Long, // Конец аренды (в формате timestamp)
    @ColumnInfo(name = "total_price") val totalPrice: Double, // Общая стоимость аренды
    @ColumnInfo(name = "car_price") val carPrice: Double, // Цена за все дни
    @ColumnInfo(name = "book_days") val bookDays: Int, // Количество дней аренды
    @ColumnInfo(name = "insurance_price") val insurancePrice: Int, // Стоимость страховки
    @ColumnInfo(name = "deposit") val deposit: Int, // Депозит
    @ColumnInfo(name = "is_ended") val isEnded: Boolean = false // Отменена ли аренда
)
