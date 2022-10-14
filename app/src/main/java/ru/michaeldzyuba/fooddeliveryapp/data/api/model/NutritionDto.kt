package ru.michaeldzyuba.fooddeliveryapp.data.api.model


data class NutritionDto(
    val caloricBreakdownDto: CaloricBreakdownDto?,
    val calories: Int?,
    val carbs: String?,
    val fat: String?,
    val nutrientDtos: List<NutrientDto?>?,
    val protein: String?
)