package ru.michaeldzyuba.fooddeliveryapp.domain

data class FoodItem(
    val itemId: Int,
    val imageUrl:String,
    val name: String,
    val description:String
)