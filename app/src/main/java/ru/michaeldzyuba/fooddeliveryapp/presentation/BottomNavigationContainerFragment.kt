package ru.michaeldzyuba.fooddeliveryapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.michaeldzyuba.fooddeliveryapp.R
import ru.michaeldzyuba.fooddeliveryapp.databinding.FragmentBottomNavigationContainerBinding

class BottomNavigationContainerFragment : Fragment() {

    private var _binding: FragmentBottomNavigationContainerBinding? = null
    private val binding: FragmentBottomNavigationContainerBinding
        get() = _binding
            ?: throw RuntimeException("FragmentBottomNavigationContainerBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomNavigationContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment = childFragmentManager.findFragmentById(
            R.id.bottomNavFragmentContainer
        ) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
    }

}