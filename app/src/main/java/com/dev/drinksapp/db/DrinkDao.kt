package com.dev.drinksapp.db

import androidx.room.*
import com.dev.drinksapp.data.model.Drink
import com.dev.drinksapp.data.model.DrinkEntity

@Dao
interface DrinkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteDrink(drink: DrinkEntity)

    @Query("SELECT * FROM drinks")
    suspend fun getAllFavoritesDrinks(): List<DrinkEntity>

    @Delete
    suspend fun deleteFavoriteDrink(drink: DrinkEntity)
}