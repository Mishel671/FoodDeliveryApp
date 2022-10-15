package ru.michaeldzyuba.fooddeliveryapp.data.api.model

data class ResponseFood(
    val expires: Long?,
    val isStale: Boolean?,
    val menuItems: List<FoodItemDto?>,
    val number: Int?,
    val offset: Int?,
    val processingTimeMs: Int?,
    val totalMenuItems: Int?,
    val type: String?
)