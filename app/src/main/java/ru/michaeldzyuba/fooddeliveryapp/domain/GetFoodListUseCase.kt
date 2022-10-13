package ru.michaeldzyuba.fooddeliveryapp.domain

import javax.inject.Inject

class GetFoodListUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    operator fun invoke() = repository.getFoodList()
}