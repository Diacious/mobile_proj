package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.data.entities.CarDetailsPartial

@Dao
interface CarDetailsDao {

    /**
     * Извлекает описание модели и адрес местонахождения для автомобиля с заданным car_id.
     *
     * @param carId Идентификатор автомобиля.
     * @return Объект CarDetailsPartial с описанием и адресом или null, если данных нет.
     */
    @Query("SELECT car_id, model_description, location_address FROM car_details WHERE car_id = :carId LIMIT 1")
    suspend fun getCarDetailsByCarId(carId: Long): CarDetailsPartial?
}
