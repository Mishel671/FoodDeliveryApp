package ru.michaeldzyuba.fooddeliveryapp.data.mapper

import ru.michaeldzyuba.fooddeliveryapp.data.api.model.FoodItemDto
import ru.michaeldzyuba.fooddeliveryapp.data.database.FoodItemDbModel
import ru.michaeldzyuba.fooddeliveryapp.domain.FoodItem

private const val INCORRECT_VALUE_INT = -1
private const val INCORRECT_VALUE_FLOAT = -1F

private var incrementer = 0

fun List<FoodItemDto?>.mapToListDbModel(requestName: String): List<FoodItemDbModel> {
    incrementer = 0
    val foodListDto = this
    return foodListDto.map { foodItemDto ->
        incrementer += 1
        foodItemDto!!.mapToDbModel(requestName)
    }

}

private fun FoodItemDto.mapToDbModel(requestName: String): FoodItemDbModel {
    val foodDto = this
    return FoodItemDbModel(
        id = incrementer,
        foodId = foodDto.id ?: INCORRECT_VALUE_INT,
        title = foodDto.title ?: "No name",
        image = foodDto.image ?: "",
        price = foodDto.price ?: INCORRECT_VALUE_FLOAT,
        requestName = requestName
    )
}

fun FoodItemDbModel.mapToItem(): FoodItem {
    val foodDbModel = this
    return FoodItem(
        id = foodDbModel.id,
        foodId = foodDbModel.foodId,
        title = foodDbModel.title,
        image = foodDbModel.image,
        price = foodDbModel.price
    )
}
