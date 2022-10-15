package ru.michaeldzyuba.fooddeliveryapp.data.api.model


data class NutritionDto(
    val caloricBreakdown: CaloricBreakdownDto?,
    val calories: Int?,
    val carbs: String?,
    val fat: String?,
    val nutrients: List<NutrientDto?>?,
    val protein: String?
)