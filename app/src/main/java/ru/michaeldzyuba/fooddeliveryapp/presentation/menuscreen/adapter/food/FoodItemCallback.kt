package ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen.adapter.food

import androidx.recyclerview.widget.DiffUtil
import ru.michaeldzyuba.fooddeliveryapp.domain.FoodItem

object FoodItemCallback : DiffUtil.ItemCallback<FoodItem>() {
    override fun areItemsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem.foodId == newItem.foodId
    }

    override fun areContentsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem == newItem
    }

}