package com.example.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.AuthorizationFragmentBinding
import com.example.test.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthorizationFragment : Fragment(R.layout.authorization_fragment) {
    private var _binding: AuthorizationFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AuthorizationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        button.setOnClickListener{
            if (editTextTextEmailAddress.text.isEmpty() || editTextTextPassword.text.isEmpty()){
                Toast.makeText(requireContext(), "Введите данные во все поля!", Toast.LENGTH_LONG).show()
            } else {
                val coroutineScope = CoroutineScope(Dispatchers.Main)
                coroutineScope.launch {
                    val database = AppDatabase.getInstance(requireContext())
                    val agentDao = database.agentDao()
                    val count: Int = agentDao.getAgentCheck(editTextTextEmailAddress.text.toString(), editTextTextPassword.text.toString())
                    if (count == 1){
                        findNavController().navigate(R.id.action_authorizationFragment_to_firstFragment)
                    }else{
                        Toast.makeText(requireContext(), "Пользователь не существует!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        button2.setOnClickListener{
            findNavController().navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }
    }
}