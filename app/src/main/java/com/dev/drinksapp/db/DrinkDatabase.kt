package com.dev.drinksapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dev.drinksapp.data.model.DrinkEntity

@Database(
    entities = [DrinkEntity::class],
    version = 1
)
abstract class DrinkDatabase: RoomDatabase() {

    abstract fun getDrinkDao(): DrinkDao

    companion object{

        @Volatile
        private var instance: DrinkDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DrinkDatabase::class.java,
                "drinks_db.db"
            ).build()
    }
}