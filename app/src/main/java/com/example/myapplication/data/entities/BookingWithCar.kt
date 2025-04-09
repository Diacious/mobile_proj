package com.example.myapplication.data.entities

import androidx.room.Embedded
import androidx.room.Relation

data class BookingWithCar(
    @Embedded val booking: Booking,
    @Relation(
        parentColumn = "car_id",
        entityColumn = "id"
    )
    val car: Car
)
