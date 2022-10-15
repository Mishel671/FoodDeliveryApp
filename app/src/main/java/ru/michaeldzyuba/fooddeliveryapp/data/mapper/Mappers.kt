package ru.michaeldzyuba.fooddeliveryapp.data.mapper

import ru.michaeldzyuba.fooddeliveryapp.data.api.model.FoodItemDto
import ru.michaeldzyuba.fooddeliveryapp.data.database.FoodItemDbModel
import ru.michaeldzyuba.fooddeliveryapp.domain.FoodItem
import ru.michaeldzyuba.fooddeliveryapp.utils.INCORRECT_STRING
import ru.michaeldzyuba.fooddeliveryapp.utils.INCORRECT_VALUE_FLOAT
import ru.michaeldzyuba.fooddeliveryapp.utils.INCORRECT_VALUE_INT


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
        title = foodDto.title ?: INCORRECT_STRING,
        image = foodDto.image ?: "",
        price = foodDto.price ?: INCORRECT_VALUE_FLOAT,
        fat = foodDto.nutrition?.fat ?: INCORRECT_STRING,
        protein = foodDto.nutrition?.protein ?: INCORRECT_STRING,
        carbs = foodDto.nutrition?.carbs ?: INCORRECT_STRING,
        calories = foodDto.nutrition?.calories?: INCORRECT_VALUE_INT,
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
        price = foodDbModel.price,
        fat = foodDbModel.fat,
        protein = foodDbModel.protein,
        carbs = foodDbModel.carbs,
        calories = foodDbModel.calories
    )
}
