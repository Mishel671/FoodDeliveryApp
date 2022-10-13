package ru.michaeldzyuba.fooddeliveryapp.domain

import androidx.lifecycle.LiveData

interface FoodRepository {

    fun getCategories():List<CategoryItem>

    fun getAds():List<AdItem>

    fun getFoodList(): LiveData<List<AdItem>>
}