package com.example.myapplication.data.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.data.dao.UserRegistrationDao
import com.example.myapplication.data.entities.UserRegistrationData
import com.example.myapplication.data.entities.Car
import com.example.myapplication.data.dao.CarDao
import com.example.myapplication.data.dao.CarDetailsDao
import  com.example.myapplication.data.entities.CarDetailsPartial
import androidx.room.migration.Migration
import com.example.myapplication.data.entities.Booking
import com.example.myapplication.data.dao.BookingDao

@Database(entities = [UserRegistrationData::class, Car::class, CarDetailsPartial::class, Booking::class], version = 6, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userRegistrationDao(): UserRegistrationDao
    abstract fun carDao(): CarDao
    abstract  fun carDetailsDao(): CarDetailsDao
    abstract fun bookingDao(): BookingDao // Добавляем DAO для бронирований


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // 🔧 Миграция с версии 4 на версию 5, создаёт таблицу car_details
        val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Добавляем таблицу bookings при миграции
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `bookings` (" +
                            "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "`car_id` INTEGER NOT NULL, " +
                            "`user_id` INTEGER NOT NULL, " +
                            "`start_date` INTEGER NOT NULL, " +
                            "`end_date` INTEGER NOT NULL, " +
                            "`total_price` INTEGER NOT NULL, " +
                            "`insurance_price` INTEGER NOT NULL, " +
                            "`deposit` INTEGER NOT NULL)"
                )
            }
        }

        private val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE cars ADD COLUMN isFavorite INTEGER NOT NULL DEFAULT 0")
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "my_prepopulated_db.db"
                )
                .createFromAsset("databases/my_prepopulated_db.db")
                .addMigrations(MIGRATION_4_5)
                .addMigrations(MIGRATION_5_6)
                .fallbackToDestructiveMigration() // 💥 ключевая строка
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}