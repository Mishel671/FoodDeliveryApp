package ru.michaeldzyuba.fooddeliveryapp.domain

import javax.inject.Inject

class GetCategoriesListUseCase @Inject constructor(
    private val repository: FoodRepository
){
    operator fun invoke() = repository.getCategories()
}