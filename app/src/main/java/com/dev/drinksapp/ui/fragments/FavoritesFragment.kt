package com.dev.drinksapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.drinksapp.vo.Resource
import com.dev.drinksapp.R
import com.dev.drinksapp.adapters.DrinksAdapter
import com.dev.drinksapp.data.DataSourceImpl
import com.dev.drinksapp.data.model.Drink
import com.dev.drinksapp.data.model.DrinkEntity
import com.dev.drinksapp.db.DrinkDatabase
import com.dev.drinksapp.repository.DrinksRepositoryImpl
import com.dev.drinksapp.ui.viewmodel.MainViewModel
import com.dev.drinksapp.ui.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment(), DrinksAdapter.OnDrinkClickListener {

    private lateinit var adapter: DrinksAdapter

    private val viewModel by viewModels<MainViewModel>{ MainViewModelFactory(
        DrinksRepositoryImpl(
            DataSourceImpl(DrinkDatabase.invoke(requireContext().applicationContext))
        )
    ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers(){
        viewModel.getFavoriteDrinks().observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val drinkList = result.data.map {
                        Drink(it.drinkId, it.drinkImage, it.drinkName, it.drinkDescription, it.drinkHasAlcohol)
                    }.toMutableList()

                    adapter = DrinksAdapter(requireContext(), drinkList, this)
                    rvFavorites.adapter = adapter
                }
                is Resource.Failure -> {}
            }
        })
    }

    private fun setupRecyclerView(){
        rvFavorites.layoutManager = LinearLayoutManager(requireContext())
        rvFavorites.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onDrinkClick(drink: Drink, position: Int) {
        viewModel.deleteFavoriteDrink(DrinkEntity(drink.drinkId, drink.image, drink.name, drink.description, drink.hasAlcohol))
        adapter.deleteFavoriteDrink(position)
        Toast.makeText(requireContext(), "Delete it!", Toast.LENGTH_LONG).show()
    }
}