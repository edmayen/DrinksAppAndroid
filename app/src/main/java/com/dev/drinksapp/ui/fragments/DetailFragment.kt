package com.dev.drinksapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dev.drinksapp.R
import com.dev.drinksapp.data.model.Drink
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable("drink")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(drink.image).centerCrop().into(imgDrinkDetail)
        tvDrinkNameDetail.text = drink.name
        tvDrinkDescriptionDetail.text = drink.description
        if(drink.hasAlcohol == "Non_Alcoholic"){
            tvHasAlcoholDetail.text = "Drink without alcohol"
        } else{
            tvHasAlcoholDetail.text = "Drink with alcohol"
        }


    }
}