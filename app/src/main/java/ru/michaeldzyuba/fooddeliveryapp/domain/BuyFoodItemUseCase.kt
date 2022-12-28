package ru.michaeldzyuba.fooddeliveryapp.domain

import javax.inject.Inject


class BuyFoodItemUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(foodItem: FoodItem) = repository.changeFoodItem(foodItem)
}