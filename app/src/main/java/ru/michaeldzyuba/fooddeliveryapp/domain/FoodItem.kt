package ru.michaeldzyuba.fooddeliveryapp.domain

data class FoodItem(
    val id: Int,
    val foodId: Int,
    val title: String,
    val image: String,
    val price: Float
)