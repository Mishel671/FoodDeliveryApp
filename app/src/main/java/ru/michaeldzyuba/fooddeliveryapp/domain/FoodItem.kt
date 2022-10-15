package ru.michaeldzyuba.fooddeliveryapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodItem(
    val id: Int,
    val foodId: Int,
    val title: String,
    val image: String,
    val price: Float,
    val fat: String,
    val protein: String,
    val carbs: String,
    val calories: Int,
) : Parcelable