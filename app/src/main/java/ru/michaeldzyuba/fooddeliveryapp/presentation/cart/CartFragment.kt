package ru.michaeldzyuba.fooddeliveryapp.presentation.cart

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.michaeldzyuba.fooddeliveryapp.FoodApp
import ru.michaeldzyuba.fooddeliveryapp.databinding.FragmentCartBinding
import ru.michaeldzyuba.fooddeliveryapp.domain.FoodItem
import ru.michaeldzyuba.fooddeliveryapp.domain.FoodRepository
import javax.inject.Inject


class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding: FragmentCartBinding
        get() = _binding ?: throw RuntimeException("")

    private val component by lazy {
        (requireActivity().application as FoodApp).component
    }

    val cartAdapter = CartAdapter()

    @Inject
    lateinit var repository: FoodRepository

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        getFoodItem()
    }

    private fun setAdapter() {
        binding.rvCart.adapter = cartAdapter
        cartAdapter.onDeleteItem = {
            deleteItem(it)
        }
    }

    private fun getFoodItem() {
        repository.loadCartFoodList().observe(viewLifecycleOwner) {
            if (it.isEmpty())
                binding.tvEmpty.visibility = View.VISIBLE
            else
                binding.tvEmpty.visibility = View.GONE
                cartAdapter.submitList(it)
        }
    }

    private fun deleteItem(item: FoodItem) {
        lifecycleScope.launch(Dispatchers.IO) {
            repository.changeFoodItem(item.copy(isCart = false))
        }
    }
}