package ru.michaeldzyuba.fooddeliveryapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ru.michaeldzyuba.fooddeliveryapp.R
import ru.michaeldzyuba.fooddeliveryapp.databinding.FragmentLoginBinding
import ru.michaeldzyuba.fooddeliveryapp.utils.AppPreferences


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw RuntimeException("FragmentLoginBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (AppPreferences.getAge() == -1 || AppPreferences.getFullName().isEmpty()) {
            binding.btnSave.setOnClickListener {
                val fullName = binding.fullName.text.toString().trim()
                val age = binding.age.text.toString()
                if (fullName.length >= 3) {
                    if (age.isNotEmpty() && age.toInt() > 0) {
                        AppPreferences.saveFullName(fullName)
                        AppPreferences.saveAge(age.toInt())
                        findNavController().navigate(R.id.action_loginFragment_to_bottomNavigationContainerFragment)
                    } else {
                        Toast.makeText(
                            requireActivity(),
                            "Возраст не может быть отрицательным",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "ФИО больше 3 символов",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            findNavController().navigate(R.id.action_loginFragment_to_bottomNavigationContainerFragment)
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}