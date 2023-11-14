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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

            val tokenModel : Call<TokenModel> = jsonAPI.authorizationByPass(emailEditText.text.toString(), passwordEditText.text.toString())
            tokenModel.enqueue(object : Callback<TokenModel>{
                override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                    if (response.isSuccessful){
                        val token = response.body()?.token
                        val bundle = createBundle(token, passwordEditText.text.toString())
                        findNavController().navigate(R.id.action_authorizationFragment_to_firstFragment, bundle)
                    } else {
                        Toast.makeText(requireContext(), "Некорректные данные", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                    Toast.makeText(requireContext(), "Возникла ошибка", Toast.LENGTH_SHORT).show()
                }
            })
        }
        button2.setOnClickListener{
            findNavController().navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }
    }



    private fun createBundle(
        token: Any?,
        password : Any?
    ): Bundle {
        val bundle = Bundle()
        bundle.putString("Token", token.toString())
        bundle.putString("Password", password.toString())
        return bundle
    }
}