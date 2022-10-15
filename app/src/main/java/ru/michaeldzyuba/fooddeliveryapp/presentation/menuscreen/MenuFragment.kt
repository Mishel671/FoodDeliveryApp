package ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import ru.michaeldzyuba.fooddeliveryapp.FoodApp
import ru.michaeldzyuba.fooddeliveryapp.R
import ru.michaeldzyuba.fooddeliveryapp.databinding.FragmentMenuBinding
import ru.michaeldzyuba.fooddeliveryapp.domain.CategoryItem
import ru.michaeldzyuba.fooddeliveryapp.presentation.BottomNavigationContainerFragmentDirections
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

    }

    private fun buttonListeners() {
        binding.chooseCountryBtn.setOnClickListener {
            requireActivity().findNavController(R.id.fragmentContainer)
                .navigate(R.id.action_bottomNavigationContainerFragment_to_chooseCountryFragment)
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
            requireActivity().findNavController(R.id.fragmentContainer)
                .navigate(
                    BottomNavigationContainerFragmentDirections
                        .actionBottomNavigationContainerFragmentToDetailMenuFragment(foodItem)
                )
        }
        binding.rvFoodList.adapter = foodAdapter
        val itemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(requireContext().getDrawable(R.drawable.divider_item)!!)
        binding.rvFoodList.addItemDecoration(itemDecoration)
        val itemDecorator = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)

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

        binding.categoryTabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val categoryItem = tabsWithData[tab]
                if (categoryItem != null) {
                    viewModel.loadData(categoryItem.queryValue)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}