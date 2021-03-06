package com.dev.drinksapp.repository

import com.dev.drinksapp.data.model.Drink
import com.dev.drinksapp.data.model.DrinkEntity
import com.dev.drinksapp.vo.Resource

interface DrinksRepository {

    suspend fun getDrinksList(drinkName: String): Resource<List<Drink>>

    suspend fun getFavoriteDrinks(): Resource<List<DrinkEntity>>

    suspend fun insertDrink(drink: DrinkEntity)

    suspend fun deleteFavoriteDrink(drink: DrinkEntity)
}