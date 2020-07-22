package com.dev.drinksapp.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dev.drinksapp.data.model.DrinkEntity

interface DrinkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteDrink(drink: DrinkEntity)

    @Query("SELECT * FROM drinks")
    suspend fun getAllFavoritesDrinks(): List<DrinkEntity>
}