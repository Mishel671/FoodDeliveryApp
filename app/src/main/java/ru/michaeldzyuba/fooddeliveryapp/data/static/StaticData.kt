package ru.michaeldzyuba.fooddeliveryapp.data.static

import ru.michaeldzyuba.fooddeliveryapp.R
import ru.michaeldzyuba.fooddeliveryapp.domain.AdItem
import ru.michaeldzyuba.fooddeliveryapp.domain.CategoryItem

fun getAdsImage() = listOf(
    AdItem(
        R.drawable.ic_ad_first
    ),
    AdItem(
        R.drawable.ic_ad_second
    )
)

//The api I am using doesn't have method that return categories
fun getCategoriesList() = listOf(
    CategoryItem(
        categoryId = 0,
        title = R.string.title_burger,
        queryValue = "burger"
    ),
    CategoryItem(
        categoryId = 1,
        title = R.string.title_dessert,
        queryValue = "dessert"
    ),
    CategoryItem(
        categoryId = 2,
        title = R.string.title_cocktail,
        queryValue = "dessert"
    )
)