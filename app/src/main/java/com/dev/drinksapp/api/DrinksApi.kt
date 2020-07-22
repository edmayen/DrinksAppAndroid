package com.dev.drinksapp.api

import com.dev.drinksapp.data.model.Drink
import com.dev.drinksapp.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinksApi {

    @GET("search.php?")
    suspend fun getDrinksByName(
        @Query(value ="s")
        drinkName: String
    ): DrinkList
}