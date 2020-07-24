package com.dev.drinksapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.drinksapp.R
import com.dev.drinksapp.adapters.DrinksAdapter
import com.dev.drinksapp.data.DataSourceImpl
import com.dev.drinksapp.data.model.Drink
import com.dev.drinksapp.db.DrinkDatabase
import com.dev.drinksapp.repository.DrinksRepositoryImpl
import com.dev.drinksapp.ui.viewmodel.MainViewModel
import com.dev.drinksapp.ui.viewmodel.MainViewModelFactory
import com.dev.drinksapp.vo.Resource
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), DrinksAdapter.OnDrinkClickListener {

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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchView()
        setupObservers()
        btnFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoritesFragment)
        }
    }

    private fun setupObservers(){
        viewModel.fetchDrinksList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    rvDrinks.adapter = DrinksAdapter(requireContext(), result.data.toMutableList(), this)
                }
                is Resource.Failure -> {
                    hideProgressBar()
                    Toast.makeText(requireContext(), "Error ${result.exception}", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setupSearchView(){
        searchDrinks.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setDrink(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun hideProgressBar(){
        progressBarDrink.visibility = View.GONE
    }

    private fun showProgressBar(){
        progressBarDrink.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        rvDrinks.layoutManager = LinearLayoutManager(requireContext())
        rvDrinks.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onDrinkClick(drink: Drink, position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("drink", drink)
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
    }
}