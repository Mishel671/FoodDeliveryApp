package ru.michaeldzyuba.fooddeliveryapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.michaeldzyuba.fooddeliveryapp.R
import ru.michaeldzyuba.fooddeliveryapp.databinding.FragmentMenuBinding
import ru.michaeldzyuba.fooddeliveryapp.databinding.FragmentUserBinding


class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding: FragmentUserBinding
        get() = _binding ?: throw RuntimeException("FragmentUserBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

}