package com.dev.drinksapp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.dev.drinksapp.data.model.Drink
import com.dev.drinksapp.data.model.DrinkEntity
import com.dev.drinksapp.repository.DrinksRepository
import com.dev.drinksapp.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel @ViewModelInject constructor(private val repo: DrinksRepository): ViewModel(){

    private val drinksData = MutableLiveData<String>()

    fun setDrink(drinkName: String){
        drinksData.value = drinkName
    }

    init {
        setDrink("margarita")
    }

    val fetchDrinksList = drinksData.distinctUntilChanged().switchMap {drinkName ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getDrinksList(drinkName))
            }catch (e: Exception){
                emit(Resource.Failure(e))
            }
        }
    }

    fun saveFavoriteDrink(drink: DrinkEntity){
        viewModelScope.launch {
            repo.insertDrink(drink)
        }
    }

    fun getFavoriteDrinks() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getFavoriteDrinks())
        } catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    fun deleteFavoriteDrink(drink: DrinkEntity){
        viewModelScope.launch {
            repo.deleteFavoriteDrink(drink)
        }
    }
}