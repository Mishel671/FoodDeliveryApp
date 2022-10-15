package ru.michaeldzyuba.fooddeliveryapp.domain

import androidx.annotation.DrawableRes

//Data class для баннера. Симуляция получения с Api.
data class AdItem(
    val id:Int,
    @DrawableRes
    val image: Int
)