package ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import ru.michaeldzyuba.fooddeliveryapp.FoodApp
import ru.michaeldzyuba.fooddeliveryapp.R
import ru.michaeldzyuba.fooddeliveryapp.databinding.FragmentMenuBinding
import ru.michaeldzyuba.fooddeliveryapp.domain.CategoryItem
import ru.michaeldzyuba.fooddeliveryapp.presentation.ViewModelFactory
import ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen.adapter.ad.AdListAdapter
import ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen.adapter.food.FoodListAdapter
import javax.inject.Inject

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding: FragmentMenuBinding
        get() = _binding ?: throw RuntimeException("FragmentMenuBinding == null")

    private val component by lazy {
        (requireActivity().application as FoodApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MenuViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabLayout()
        setupAdRecyclerView()
        setupFoodRecyclerView()
        buttonListeners()
        viewModel.errorLoad.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }
    }



    private fun buttonListeners() {
        setFragmentResultListener(CITY_RESULT_KEY) { key, bundle ->
            val city = bundle.getString(CITY_RESULT_ITEM_KEY)
            if (city is String) binding.tvCity.text = city
        }
        binding.chooseCountryBtn.setOnClickListener {
            findNavController()
                .navigate(R.id.action_menuFragment_to_chooseCountryFragment2)
        }
        binding.btnQR.setOnClickListener {
            try {
                val intent = Intent("android.media.action.IMAGE_CAPTURE")
                requireActivity().startActivityForResult(intent, 1)
            } catch (e: Exception) {
                Toast.makeText(requireActivity(), "Give camera permission", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setupAdRecyclerView() {
        val adAdapter = AdListAdapter()
        with(binding.rvAdList) {
            adapter = adAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
        adAdapter.submitList(viewModel.getAdsList())

    }

    private fun setupFoodRecyclerView() {
        val foodAdapter = FoodListAdapter(requireContext())
        foodAdapter.onClick = { foodItem ->
//            requireActivity().findNavController(R.id.fragmentContainer)
//                .navigate(
//                    BottomNavigationContainerFragmentDirections
//                        .actionBottomNavigationContainerFragmentToDetailMenuFragment(foodItem)
//                )
        }
        foodAdapter.onClickBuy = { foodItem ->
            viewModel.buyFoodItem(foodItem)
        }
        binding.rvFoodList.adapter = foodAdapter
        val itemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.divider_item
            )!!
        )
        binding.rvFoodList.addItemDecoration(itemDecoration)

        viewModel.foodList.observe(viewLifecycleOwner) {
            foodAdapter.submitList(it)
        }
    }

    private fun setupTabLayout() {
        val listTabs = viewModel.getCategoriesList()
        val tabsWithData = HashMap<TabLayout.Tab, CategoryItem>()
        with(binding.categoryTabLayout) {
            listTabs.forEach { categoryItem ->
                val tab = newTab().setText(categoryItem.title)
                addTab(tab)
                tabsWithData[tab] = categoryItem
            }
        }

        //Save chosen tab
        val tabSet = tabsWithData.filterValues { it == viewModel.getActiveCategory() }.keys
        tabSet.forEach { tab ->
            tab.select()
        }

        binding.categoryTabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val categoryItem = tabsWithData[tab]
                if (categoryItem != null) {
                    viewModel.loadData(categoryItem)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    companion object {
        const val CITY_RESULT_KEY = "spec_result_key"
        const val CITY_RESULT_ITEM_KEY = "spec_result_item_key"
    }
}