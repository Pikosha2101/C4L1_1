package com.example.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.FirstFragmentBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirstFragment : Fragment(R.layout.first_fragment) {
    private var _binding : FirstFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var token : String
    private lateinit var number : String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FirstFragmentBinding.inflate(inflater, container, false)
        token = arguments?.getString("Token")!!
        binding.passwordEditText.isClickable = false
        binding.passwordEditText.isFocusableInTouchMode = false
        binding.phoneEditText.isClickable = false
        binding.emailEditText.isClickable = false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {

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

        val user : Call<UserModel> = jsonAPI.getUserInfo(token)
        user.enqueue(object : Callback<UserModel> {
            override fun onResponse(
                call: Call<UserModel>,
                response: Response<UserModel>
            ) {
                number = response.body()?.phone.toString()
                if (response.isSuccessful){
                    emailEditText.setText(response.body()?.email)
                    firstNameEditText.setText(response.body()?.firstname)
                    lastNameEditText.setText(response.body()?.lastname)
                    patronymicEditText.setText(response.body()?.patronymic)
                    phoneEditText.setText(response.body()?.phone)

                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Toast.makeText(requireContext(), "Возникла ошибка", Toast.LENGTH_SHORT).show()
            }
        })
        /*if (user.isSuccessful) {
            emailEditText.setText(user.body()?.email)
            firstNameEditText.setText(user.body()?.firstname)
            lastNameEditText.setText(user.body()?.lastname)
            patronymicEditText.setText(user.body()?.patronymic)
            phoneEditText.setText(user.body()?.phone)
        } else {
            Toast.makeText(requireContext(), "Возникла ошибка", Toast.LENGTH_SHORT).show()
        }*/



        button.setOnClickListener{
            findNavController().navigate(R.id.action_firstFragment_to_authorizationFragment)
        }



        button3.setOnClickListener{
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

            val user : Call<UserModel> = jsonAPI.changeUser(token, UserModel(
                emailEditText.text.toString(),
                firstNameEditText.text.toString(),
                lastNameEditText.text.toString(),
                passwordEditText.text.toString(),
                patronymicEditText.text.toString()
            ))
            user.enqueue(object : Callback<UserModel> {
                override fun onResponse(
                    call: Call<UserModel>,
                    response: Response<UserModel>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(requireContext(), "Изменено", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    Toast.makeText(requireContext(), "Возникла ошибка", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}