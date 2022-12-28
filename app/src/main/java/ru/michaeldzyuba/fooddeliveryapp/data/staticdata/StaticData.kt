package ru.michaeldzyuba.fooddeliveryapp.data.staticdata

import ru.michaeldzyuba.fooddeliveryapp.R
import ru.michaeldzyuba.fooddeliveryapp.domain.AdItem
import ru.michaeldzyuba.fooddeliveryapp.domain.CategoryItem

fun getAdsImage() = listOf(
    AdItem(
        0,
        R.drawable.ic_ad_first
    ),
    AdItem(
        1,
        R.drawable.ic_ad_second
    ),
    AdItem(
        2,
        R.drawable.ic_ad_first
    ),
    AdItem(
        3,
        R.drawable.ic_ad_second
    ),
    AdItem(
        4,
        R.drawable.ic_ad_first
    ),
    AdItem(
        5,
        R.drawable.ic_ad_second
    ),
)

//The api I am using doesn't have method that return categories
fun getCategoriesList() = listOf(
    CategoryItem(
        title = R.string.title_burger,
        queryValue = "burger"
    ),
    CategoryItem(
        title = R.string.title_meat,
        queryValue = "meat"
    ),
    CategoryItem(
        title = R.string.title_dessert,
        queryValue = "dessert"
    ),
    CategoryItem(
        title = R.string.title_cocktail,
        queryValue = "cocktail"
    )
)

fun getCities() = listOf(
    "Оренбург",
    "Москва",
    "Московская область",
    "Санкт-Петербург",
    "Адыгея",
    "Алтай",
    "Алтайский край",
    "Амурская область",
    "Архангельская область",
    "Астраханская область",
    "Башкоторстан",
    "Белогородская область",
    "Брянская область",
    "Бурятия",
    "Владимирска область",
    "Волгоградская область",
    "Вологодская область",
    "Воронежская область",
    "Дагестан",
    "Ивановская область",
    "Иркутская область",
)