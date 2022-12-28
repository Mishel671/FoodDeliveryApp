package ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen.city

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import ru.michaeldzyuba.fooddeliveryapp.data.staticdata.getCities
import ru.michaeldzyuba.fooddeliveryapp.databinding.FragmentCityBinding
import ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen.MenuFragment


class CityFragment : Fragment() {

    private var _binding: FragmentCityBinding? = null
    private val binding: FragmentCityBinding
        get() = _binding ?: throw RuntimeException("FragmentCartBinding == null")

    private val cityAdapter = CityAdapter(getCities())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityAdapter.onClickItem = {
            setFragmentResult(
                MenuFragment.CITY_RESULT_KEY,
                bundleOf(MenuFragment.CITY_RESULT_ITEM_KEY to it)
            )
            parentFragmentManager.popBackStack()
        }
        binding.rvCity.adapter = cityAdapter
    }

    companion object {

        fun newInstance() =
            CityFragment()
    }
}