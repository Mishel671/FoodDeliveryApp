package ru.michaeldzyuba.fooddeliveryapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import ru.michaeldzyuba.fooddeliveryapp.R
import ru.michaeldzyuba.fooddeliveryapp.data.staticdata.getCities
import ru.michaeldzyuba.fooddeliveryapp.databinding.FragmentChooseCountryBinding
import ru.michaeldzyuba.fooddeliveryapp.databinding.FragmentCityBinding
import ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen.MenuFragment
import ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen.city.CityAdapter
import ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen.city.CityFragment


class ChooseCountryFragment : Fragment() {

    private var _binding: FragmentChooseCountryBinding? = null
    private val binding: FragmentChooseCountryBinding
        get() = _binding ?: throw RuntimeException("FragmentCartBinding == null")

    private val cityAdapter = CityAdapter(getCities())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityAdapter.onClickItem = {
            setFragmentResult(
                MenuFragment.CITY_RESULT_KEY,
                bundleOf(MenuFragment.CITY_RESULT_ITEM_KEY to it)
            )
            findNavController().popBackStack()
        }
        binding.rvCity.adapter = cityAdapter
    }

    companion object {

        fun newInstance() =
            ChooseCountryFragment()
    }

}