package ru.michaeldzyuba.fooddeliveryapp.domain

import javax.inject.Inject

class GetAdsListUseCase @Inject constructor(
    private val repository: FoodRepository
){
    operator fun invoke() = repository.getAds()
}