package com.example.test.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.FirstInputFragmentBinding

class FirstInputFragment : Fragment(R.layout.first_input_fragment) {
    private var _binding : FirstInputFragmentBinding? = null
    private val binding get() = _binding!!

    private val MY_SETTINGS : String = "my_settings"
    private lateinit var sp : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FirstInputFragmentBinding.inflate(inflater, container, false)

        sp = requireContext().getSharedPreferences(MY_SETTINGS, 0)

        val hasVisited : Boolean = sp.getBoolean("hasVisited", false)
        if(!hasVisited){
            val editor : SharedPreferences.Editor = sp.edit()
            editor.putBoolean("hasVisited", true)
            editor.apply()
        } else {
            findNavController().navigate(R.id.action_firstInputFragment_to_authorizationFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        button.setOnClickListener{
            findNavController().navigate(R.id.action_firstInputFragment_to_authorizationFragment)
        }
    }
}