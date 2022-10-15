package ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen.adapter.ad

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.michaeldzyuba.fooddeliveryapp.databinding.AdItemBinding
import ru.michaeldzyuba.fooddeliveryapp.domain.AdItem

class AdListAdapter : ListAdapter<AdItem, AdItemViewHolder>(AdItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdItemViewHolder {
        val binding = AdItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AdItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdItemViewHolder, position: Int) {
        val binding = holder.binding
        val item = getItem(position)
        binding.ivAd.setImageResource(item.image)
    }
}