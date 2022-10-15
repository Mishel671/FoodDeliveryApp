package ru.michaeldzyuba.fooddeliveryapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import ru.michaeldzyuba.fooddeliveryapp.databinding.FragmentDetailMenuBinding

class DetailMenuFragment : Fragment() {

    private var _binding: FragmentDetailMenuBinding? = null
    private val binding: FragmentDetailMenuBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailMenuBinding == null")

    private val args by navArgs<DetailMenuFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.result.text = args.foodItem.toString()
    }
}