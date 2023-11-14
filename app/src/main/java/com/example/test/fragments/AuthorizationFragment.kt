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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
            CoroutineScope(Dispatchers.Main).launch {
                /*try {*/
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

                    val response = jsonAPI.authorizationByPass(emailEditText.text.toString(), passwordEditText.text.toString())

                    if (response.isSuccessful) {
                        findNavController().navigate(R.id.action_authorizationFragment_to_firstFragment)
                    } else {
                        Toast.makeText(requireContext(), "Возникла ошибка", Toast.LENGTH_SHORT).show()
                    }
            }
        }
        button2.setOnClickListener{
            findNavController().navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }
    }
}