package ru.michaeldzyuba.fooddeliveryapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_list")
data class FoodItemDbModel(
    val id: Int,
    @PrimaryKey()
    val foodId: Int,
    val title: String,
    val image: String,
    val price: Float,
    val fat: String,
    val protein: String,
    val carbs: String,
    val calories: Int,
    val requestName: String,
    val isCart: Boolean? = false
)