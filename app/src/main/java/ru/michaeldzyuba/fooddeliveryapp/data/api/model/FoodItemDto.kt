package ru.michaeldzyuba.fooddeliveryapp.data.api.model

import com.google.gson.annotations.SerializedName


data class FoodItemDto(
    val badges: List<Any?>?,//In all api results, the list is empty
    val breadcrumbs: List<String?>?,
    val generatedText: String?,//In all api results, the value is null
    val id: Int?,
    val image: String?,
    val imageType: String?,
    val images: List<String?>?,
    val likes: Int?,
    val numberOfServings: Int?,
    @SerializedName("nutrition")
    val nutrition: NutritionDto?,
    val price: Float?,//In all api results, the value is null
    val readableServingSize: String?,
    val restaurantChain: String?,
    val servingSize: String?,
    val servingsDto: ServingsDto?,
    val spoonacularScore: Any?,//In all api results, the value is null
    val title: String?
)