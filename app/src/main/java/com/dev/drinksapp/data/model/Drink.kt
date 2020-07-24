package com.dev.drinksapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    @SerializedName("idDrink")
    val drinkId: String = "",
    @SerializedName("strDrinkThumb")
    val image: String = "",
    @SerializedName("strDrink")
    val name: String = "",
    @SerializedName("strInstructions")
    val description: String = "",
    @SerializedName("strAlcoholic")
    val hasAlcohol: String = "Non_Alcoholic"
): Parcelable

@Entity
data class DrinkList(
    @SerializedName("drinks")
    val drinkList: List<Drink>
)

@Entity(tableName = "drinks")
data class DrinkEntity(
    @PrimaryKey
    val drinkId: String,
    @ColumnInfo(name = "name_drink")
    val drinkName: String,
    @ColumnInfo(name = "image_drink")
    val drinkImage: String,
    @ColumnInfo(name = "description_drink")
    val drinkDescription: String,
    @ColumnInfo(name = "has_alcohol_drink")
    val drinkHasAlcohol: String
)