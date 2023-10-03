package com.example.test.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.RegistrationFragmentBinding
import com.example.test.room.Agent
import com.example.test.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment(R.layout.registration_fragment) {
    private var _binding: RegistrationFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegistrationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        button1.setOnClickListener{
            if (login1.text.isEmpty() || editTextTextPersonName.text.isEmpty() || editTextTextPassword1.text.isEmpty() || editTextTextPassword2.text.isEmpty())
            {
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
            } else {
                val coroutineScope = CoroutineScope(Dispatchers.Main)
                coroutineScope.launch {
                    val database = AppDatabase.getInstance(requireContext())
                    val count: Int = database.agentDao().getAgentCount() + 1

                    if (editTextTextPassword1.text.toString() == editTextTextPassword2.text.toString()){
                        if (database.agentDao().getAgentCheck(login1.text.toString(), editTextTextPassword1.text.toString()) == 0){
                            val agent = Agent(count, login1.text.toString(), editTextTextPersonName.text.toString(), editTextTextPassword1.text.toString())

                            database.agentDao().insert(agent)
                            val agents = database.agentDao().getAllAgents()
                            Log.d("Agents", agents.toString())

                            Toast.makeText(requireContext(), "Аккаунт создан!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), "Аккаунт уже существует!", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "Пароли различаются!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        button2.setOnClickListener{
            findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
        }
    }
}