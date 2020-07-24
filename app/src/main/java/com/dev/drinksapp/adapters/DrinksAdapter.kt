package com.dev.drinksapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.drinksapp.R
import com.dev.drinksapp.base.BaseViewHolder
import com.dev.drinksapp.data.model.Drink
import kotlinx.android.synthetic.main.item_drinks.view.*

class DrinksAdapter(
    private val context: Context,
    private val drinksList: MutableList<Drink>,
    private val itemClickListener: OnDrinkClickListener
): RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnDrinkClickListener{
        fun onDrinkClick(drink: Drink, position: Int)
    }

    fun deleteFavoriteDrink(position: Int){
        drinksList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.item_drinks, parent, false))
    }

    override fun getItemCount(): Int {
        return drinksList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(drinksList[position], position)
        }
    }

    inner class MainViewHolder(itemView: View): BaseViewHolder<Drink>(itemView){
        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.image).centerCrop().into(itemView.imgDrink)
            itemView.tvDrinkName.text = item.name
            itemView.tvDrinkDescription.text = item.description
            itemView.setOnClickListener { itemClickListener.onDrinkClick(item, position) }
        }

    }
}