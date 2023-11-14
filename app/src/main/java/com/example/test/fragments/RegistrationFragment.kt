package com.example.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.RegistrationFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

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
        button1.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val retrofit = Retrofit.Builder()
                        .baseUrl("http://82.146.37.164:8090/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    val jsonAPI = retrofit.create(JsonAPI::class.java)

                    val userModel = UserModel(
                        emailEditText.text.toString(),
                        firstNameEditText.text.toString(),
                        lastNameEditText.text.toString(),
                        passwordEditText.text.toString(),
                        patronymicEditText.text.toString(),
                        phoneEditText.text.toString()
                    )

                    val response = jsonAPI.registrationByPass(userModel)
                    if (response.isSuccessful) {
                        Toast.makeText(requireContext(), "odfkofdk", Toast.LENGTH_SHORT).show()
                    } else {
                        // Обработка ошибки HTTP 400
                        val errorBody = response.errorBody()?.string()
                        Toast.makeText(
                            requireContext(),
                            "Ошибка регистрации: $errorBody",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (e: HttpException) {
                    // Обработка ошибки HTTP 400
                    val errorBody = e.response()?.errorBody()?.string()
                    Toast.makeText(
                        requireContext(),
                        "Ошибка регистрации: $errorBody",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        button2.setOnClickListener{
            findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
        }
    }
}