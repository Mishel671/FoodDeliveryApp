package ru.michaeldzyuba.fooddeliveryapp.domain

import androidx.lifecycle.LiveData
import ru.michaeldzyuba.fooddeliveryapp.data.api.model.ResponseFood

interface FoodRepository {

    fun getCategories():List<CategoryItem>

    fun getAds():List<AdItem>

    fun getFoodList(foodName:String): LiveData<List<FoodItem>>

    suspend fun loadFoodList(foodName:String)
}