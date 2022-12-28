package ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen.adapter.food

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import ru.michaeldzyuba.fooddeliveryapp.R
import ru.michaeldzyuba.fooddeliveryapp.databinding.FoodItemBinding
import ru.michaeldzyuba.fooddeliveryapp.domain.FoodItem
import ru.michaeldzyuba.fooddeliveryapp.utils.INCORRECT_VALUE_FLOAT

class FoodListAdapter(
    private val context: Context
) : ListAdapter<FoodItem, FoodItemViewHolder>(FoodItemCallback) {

    var onClick: ((FoodItem) -> Unit)? = null
    var onClickBuy: ((FoodItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val binding = FoodItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FoodItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        val binding = holder.binding
        val foodItem = getItem(position)
        with(binding) {
            tvFoodName.text = foodItem.title
            tvFat.text = formatString(R.string.fat_description, foodItem.fat)
            tvProtein.text = formatString(R.string.protein_description, foodItem.protein)
            tvCarbs.text = formatString(R.string.carbs_description, foodItem.carbs)
            tvCalories.text =
                formatString(R.string.calories_description, foodItem.calories.toString())
            tvCost.text = if (foodItem.price == INCORRECT_VALUE_FLOAT)
                "Buy"
            else
                formatString(R.string.cost_text, foodItem.price.toString())

            Picasso.get()
                .load(foodItem.image)
                .error(R.drawable.ic_error_food)
                .placeholder(R.drawable.ic_placeholder_food)
                .into(binding.ivFoodImage)

            root.setOnClickListener {
                onClick?.invoke(foodItem)
            }
            binding.tvCost.setOnClickListener{
                onClickBuy?.invoke(foodItem)
            }
        }

    }

    private fun formatString(@StringRes stringId: Int, value: String): String {
        return String.format(context.getString(stringId), value)
    }
}