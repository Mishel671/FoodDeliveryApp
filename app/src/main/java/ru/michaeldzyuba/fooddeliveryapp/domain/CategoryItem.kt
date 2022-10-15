package ru.michaeldzyuba.fooddeliveryapp.domain

import androidx.annotation.StringRes

data class CategoryItem(
    @StringRes
    val title: Int,
    val queryValue: String
)