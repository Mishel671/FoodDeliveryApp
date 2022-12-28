package ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen.city

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.michaeldzyuba.fooddeliveryapp.R
import ru.michaeldzyuba.fooddeliveryapp.databinding.CartItemBinding
import ru.michaeldzyuba.fooddeliveryapp.databinding.CityItemBinding
import ru.michaeldzyuba.fooddeliveryapp.domain.FoodItem
import ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen.adapter.food.FoodItemCallback

class CityAdapter(
    private val cityList: List<String>
) :
    RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    class CityViewHolder(
        val binding: CityItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    var onClickItem: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = CityItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val binding = holder.binding
        val item = cityList[position]
        binding.cityName.text = item
        binding.root.setOnClickListener{
            onClickItem?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

}