package ru.michaeldzyuba.fooddeliveryapp.presentation.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.michaeldzyuba.fooddeliveryapp.R
import ru.michaeldzyuba.fooddeliveryapp.databinding.CartItemBinding
import ru.michaeldzyuba.fooddeliveryapp.domain.FoodItem
import ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen.adapter.food.FoodItemCallback

class CartAdapter : ListAdapter<FoodItem, CartAdapter.CartViewHolder>(FoodItemCallback) {

    class CartViewHolder(
        val binding: CartItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    var onDeleteItem: ((FoodItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val binding = holder.binding
        val item = getItem(position)
        var itemCount = 1
        binding.itemCount.text = itemCount.toString()
        binding.btnMinus.setOnClickListener {
            itemCount -= 1
            if (itemCount > 0) {
                binding.itemCount.text = itemCount.toString()
            } else {
                onDeleteItem?.invoke(item)
            }
        }
        binding.btnPlus.setOnClickListener {
            itemCount += 1
            binding.itemCount.text = itemCount.toString()
        }
        binding.foodName.text = item.title
        Picasso.get()
            .load(item.image)
            .error(R.drawable.ic_error_food)
            .placeholder(R.drawable.ic_placeholder_food)
            .into(binding.foodImage)

    }

}