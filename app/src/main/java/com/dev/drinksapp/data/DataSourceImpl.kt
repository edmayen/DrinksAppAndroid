package com.dev.drinksapp.data

import com.dev.drinksapp.data.model.Drink
import com.dev.drinksapp.data.model.DrinkEntity
import com.dev.drinksapp.db.DrinkDao
import com.dev.drinksapp.db.DrinkDatabase
import com.dev.drinksapp.repository.DataSourceRepository
import com.dev.drinksapp.vo.Resource
import com.dev.drinksapp.vo.RetrofitClient
import javax.inject.Inject

class DataSourceImpl @Inject constructor(private val drinkDao: DrinkDao): DataSourceRepository {

    override suspend fun getDrinkByName(drinkName: String): Resource<List<Drink>>{
        return Resource.Success(RetrofitClient.api.getDrinksByName(drinkName).drinkList)
    }

    override suspend fun insertDrinkIntoRoom(drink: DrinkEntity){
        drinkDao.insertFavoriteDrink(drink)
    }

    override suspend fun getFavoriteDrinks(): Resource<List<DrinkEntity>> {
        return Resource.Success(drinkDao.getAllFavoritesDrinks())
    }

    override suspend fun deleteFavoriteDrink(drink: DrinkEntity) {
        drinkDao.deleteFavoriteDrink(drink)
    }


    //val generateDrinksList = Resource.Success(listOf(
      //  Drink("https://cdn5.recetasdeescandalo.com/wp-content/uploads/2018/09/Coctel-margarita-como-prepararlo.-Receta-e-ingredientes.jpg", "Margarita", "Vodka"),
        //Drink("https://franchoxbar.files.wordpress.com/2018/07/img_20180630_181858_826.jpg", "Fernet", "Fernet con Coca Cola"),
        //Drink("https://cdn.kiwilimon.com/recetaimagen/12746/th5-640x426-18280.jpg", "Paloma", "Tequila con squirt"),
        //Drink("https://t1.rg.ltmcdn.com/es/images/7/8/4/img_charronegro_de_tequila_41487_600.jpg", "Charro Negro", "Tequila con Coca Cola"),
        //Drink("https://animalgourmet.com/wp-content/uploads/2017/05/perlas_negras-bebidas_MILIMA20161007_0341_8.jpg", "Perlas Negras", "Jagger con Bost")
    //))
}