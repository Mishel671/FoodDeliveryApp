package ru.michaeldzyuba.fooddeliveryapp.data.api.model


data class NutrientDto(
    val amount: Double?,
    val name: String?,
    val percentOfDailyNeeds: Double?,
    val unit: String?
)