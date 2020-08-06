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
}