package com.example.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.FirstFragmentBinding

class FirstFragment : Fragment(R.layout.first_fragment) {
    private var _binding : FirstFragmentBinding? = null
    private val binding get() = _binding!!
    private val PREFS_NAME = "UserPrefs"
    private val KEY_LOGIN_COUNT = "loginCount"
    private val KEY_MOST_LOGGED_IN_USER = "mostLoggedInUser"
    private lateinit var mostLoggedInUser : String
    private lateinit var mostLoggedInCount : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, 0)

        // Получите логин пользователя, который авторизовался больше всех раз
        mostLoggedInUser = sharedPreferences.getString(KEY_MOST_LOGGED_IN_USER, "").toString()

        // Получите количество входов этого пользователя
        mostLoggedInCount = sharedPreferences.getInt("$mostLoggedInUser$KEY_LOGIN_COUNT", 0).toString()

        _binding = FirstFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        button.setOnClickListener{
            findNavController().navigate(R.id.action_firstFragment_to_authorizationFragment)
        }
        textView.text = "Максимальное количество авторизаций - Логин: " + mostLoggedInUser + ", количество авторизаций: " + mostLoggedInCount
    }
}