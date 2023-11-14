package com.example.test.fragments

import com.example.test.TokenModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface JsonAPI {
    @POST("user/register")
    suspend fun registrationByPass(@Body userModel: UserModel?) : Response<UserModel>

    @GET("user/login")
    suspend fun authorizationByPass(
        @Query("email") email: String,
        @Query("password") password: String
    ) : Response<TokenModel>
}