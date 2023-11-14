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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://82.146.37.164:8090/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val jsonAPI = retrofit.create(JsonAPI::class.java)

            val userModel: Call<UserModel> = jsonAPI.registrationByPass(UserModel(
                emailEditText.text.toString(),
                firstNameEditText.text.toString(),
                lastNameEditText.text.toString(),
                passwordEditText.text.toString(),
                patronymicEditText.text.toString(),
                phoneEditText.text.toString()))

            userModel.enqueue(object : Callback<UserModel>{
                override fun onResponse(
                    call: Call<UserModel>,
                    response: Response<UserModel>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(requireContext(), "Регистрация выполнена успешно", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
                }

            })
        }



        button2.setOnClickListener{
            findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
        }
    }
}