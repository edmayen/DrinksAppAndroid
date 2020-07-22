package com.dev.drinksapp.ui.viewmodel

import androidx.lifecycle.*
import com.dev.drinksapp.repository.DrinksRepository
import com.dev.drinksapp.vo.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val repo: DrinksRepository): ViewModel(){

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
}