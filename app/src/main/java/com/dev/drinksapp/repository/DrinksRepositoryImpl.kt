package com.dev.drinksapp.repository

import com.dev.drinksapp.data.model.Drink
import com.dev.drinksapp.data.model.DrinkEntity
import com.dev.drinksapp.vo.Resource

class DrinksRepositoryImpl(private val dataSource: DataSourceRepository): DrinksRepository {
    override suspend fun getDrinksList(drinkName: String): Resource<List<Drink>> {
        return dataSource.getDrinkByName(drinkName)
    }

    override suspend fun getFavoriteDrinks(): Resource<List<DrinkEntity>> {
        return dataSource.getFavoriteDrinks()
    }

    override suspend fun insertDrink(drink: DrinkEntity) {
        dataSource.insertDrinkIntoRoom(drink)
    }

    override suspend fun deleteFavoriteDrink(drink: DrinkEntity) {
        dataSource.deleteFavoriteDrink(drink)
    }
}