package ru.michaeldzyuba.fooddeliveryapp.domain

import androidx.annotation.StringRes

data class CategoryItem(
    val categoryId: Int,
    @StringRes
    val title: Int,
    val queryValue: String
)