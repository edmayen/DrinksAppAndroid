package com.dev.drinksapp.repository

import com.dev.drinksapp.data.model.Drink
import com.dev.drinksapp.data.model.DrinkEntity
import com.dev.drinksapp.vo.Resource
import com.dev.drinksapp.vo.RetrofitClient

interface DataSourceRepository {
    suspend fun getDrinkByName(drinkName: String): Resource<List<Drink>>

    suspend fun insertDrinkIntoRoom(drink: DrinkEntity)

    suspend fun getFavoriteDrinks(): Resource<List<DrinkEntity>>

    suspend fun deleteFavoriteDrink(drink: DrinkEntity)
}