package com.dev.drinksapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.drinksapp.repository.DrinksRepository

class MainViewModelFactory(private val repo: DrinksRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DrinksRepository::class.java).newInstance(repo)
    }

}