package ru.michaeldzyuba.fooddeliveryapp.domain

import javax.inject.Inject


class LoadFoodListUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(foodName: String) = repository.loadFoodList(foodName)
}