package com.dev.drinksapp.repository

import com.dev.drinksapp.data.DataSource
import com.dev.drinksapp.data.model.Drink
import com.dev.drinksapp.vo.Resource

class DrinksRepositoryImpl(private val dataSource: DataSource): DrinksRepository {
    override suspend fun getDrinksList(drinkName: String): Resource<List<Drink>> {
        return dataSource.getDrinkByName(drinkName)
    }

}