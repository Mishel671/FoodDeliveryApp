package ru.michaeldzyuba.fooddeliveryapp.data.api.model

import com.google.gson.annotations.SerializedName


data class ResponseFood(
    val expires: Long?,
    val isStale: Boolean?,
    @SerializedName("menuItems")
    val foodList: List<FoodItemDto?>,
    val number: Int?,
    val offset: Int?,
    val processingTimeMs: Int?,
    val totalMenuItems: Int?,
    val type: String?
)