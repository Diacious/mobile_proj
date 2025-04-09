package com.example.myapplication.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.ColumnInfo
import androidx.room.Index

/**
 * CarDetailsPartial - модель для частичного извлечения данных из таблицы car_details.
 * Содержит описание модели и адрес местонахождения.
 */
@Entity(
    tableName = "car_details",
    foreignKeys = [
        ForeignKey(
            entity = Car::class,
            parentColumns = ["id"],
            childColumns = ["car_id"],
            onDelete = ForeignKey.NO_ACTION // 👈 убираем каскадное удаление
        )
    ],
    indices = [Index(value = ["car_id"])]
)
data class CarDetailsPartial(
    @PrimaryKey @ColumnInfo(name = "car_id") val carId: Long,
    @ColumnInfo(name = "model_description") val model_description: String,
    @ColumnInfo(name = "location_address") val location_address: String
)
