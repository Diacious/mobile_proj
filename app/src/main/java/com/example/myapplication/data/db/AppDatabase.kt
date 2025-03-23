package com.example.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.dao.UserRegistrationDao
import com.example.myapplication.data.entities.UserRegistrationData
import com.example.myapplication.data.entities.Car
import com.example.myapplication.data.dao.CarDao

@Database(entities = [UserRegistrationData::class, Car::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userRegistrationDao(): UserRegistrationDao
    abstract fun carDao(): CarDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "my_prepopulated_db.db"
                )
                .createFromAsset("databases/my_prepopulated_db.db")
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}