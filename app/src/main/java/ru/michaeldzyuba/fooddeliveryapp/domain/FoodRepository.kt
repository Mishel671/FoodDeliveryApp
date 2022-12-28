package ru.michaeldzyuba.fooddeliveryapp.domain

import androidx.lifecycle.LiveData

interface FoodRepository {

    fun getCategories(): List<CategoryItem>

    fun getAds(): List<AdItem>

    fun getFoodList(foodName: String): LiveData<List<FoodItem>>

    suspend fun loadFoodList(foodName: String): String?

    fun loadCartFoodList(): LiveData<List<FoodItem>>

    suspend fun changeFoodItem(foodItem: FoodItem)
}