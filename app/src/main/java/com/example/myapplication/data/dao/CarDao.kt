package com.example.myapplication.data.dao

import androidx.room.*
import com.example.myapplication.data.entities.Car

@Dao
interface CarDao {

    @Query("SELECT * FROM cars")
    suspend fun getAllCars(): List<Car>

    @Query("""
        SELECT * FROM cars 
        WHERE 
            brand LIKE '%' || :query || '%' 
            OR model LIKE '%' || :query || '%' 
            OR fuelType LIKE '%' || :query || '%' 
            OR transmissionType LIKE '%' || :query || '%'
    """)
    suspend fun searchCars(query: String): List<Car>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: Car)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cars: List<Car>)

    @Update
    suspend fun updateCar(car: Car)

    @Delete
    suspend fun deleteCar(car: Car)
}
