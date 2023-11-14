package com.example.test.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.AuthorizationFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthorizationFragment : Fragment(R.layout.authorization_fragment) {
    private var _binding: AuthorizationFragmentBinding? = null
    private val binding get() = _binding!!

    private val PREFS_NAME = "UserPrefs"
    private val KEY_LOGIN_COUNT = "loginCount"
    private val KEY_MOST_LOGGED_IN_USER = "mostLoggedInUser"

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
                /*val coroutineScope = CoroutineScope(Dispatchers.Main)
                coroutineScope.launch {
                    val database = AppDatabase.getInstance(requireContext())
                    val agentDao = database.agentDao()
                    val cnt: Int = agentDao.getAgentCheck(editTextTextEmailAddress.text.toString(), editTextTextPassword.text.toString())
                    if (cnt == 1) {
                        val username = editTextTextEmailAddress.text.toString()
                        val sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, 0)
                        //получение кол-ва заходов у текущего юзера
                        val currentCount = sharedPreferences.getInt(username + KEY_LOGIN_COUNT, 0)
                        //это кол-во + 1
                        sharedPreferences.edit().putInt(username + KEY_LOGIN_COUNT, currentCount + 1).apply()

                        //самый юзер
                        val mostLoggedInUser = sharedPreferences.getString(KEY_MOST_LOGGED_IN_USER, "")
                        //его кол-во
                        val mostLoggedInCount = sharedPreferences.getInt(mostLoggedInUser + KEY_LOGIN_COUNT, 0)
                        // Если самая часто авторизующаяся учетка - обновление ее
                        if (currentCount >= mostLoggedInCount) {
                            sharedPreferences.edit().putString(KEY_MOST_LOGGED_IN_USER, username).apply()
                        }
                        findNavController().navigate(R.id.action_authorizationFragment_to_firstFragment)
                    }else {
                        Toast.makeText(requireContext(), "Пользователь не существует!", Toast.LENGTH_LONG).show()
                    }

                }*/
                findNavController().navigate(R.id.action_authorizationFragment_to_firstFragment)
            }
        }
        button2.setOnClickListener{
            findNavController().navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }
    }
}