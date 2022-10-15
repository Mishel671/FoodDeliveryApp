package ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen.adapter.ad

import androidx.recyclerview.widget.DiffUtil
import ru.michaeldzyuba.fooddeliveryapp.domain.AdItem

object AdItemCallback : DiffUtil.ItemCallback<AdItem>() {
    override fun areItemsTheSame(oldItem: AdItem, newItem: AdItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AdItem, newItem: AdItem): Boolean {
        return oldItem == newItem
    }

}