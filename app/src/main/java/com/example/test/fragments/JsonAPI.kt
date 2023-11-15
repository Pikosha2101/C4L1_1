package com.example.test.fragments

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface JsonAPI {
    @POST("user/register")
    fun registrationByPass(@Body userModel: UserModel?) : Call<ResponseBody>

    @GET("user/login")
    fun authorizationByPass(
        @Query("email") email: String,
        @Query("password") password: String
    ) : Call<TokenModel>

    @GET("user/info")
    fun getUserInfo(
        @Header("Authorization") token: String
    ) : Call<UserModel>

    @PUT("user/change/about")
    fun changeUser(
        @Header("Authorization") token: String,
        @Body userModel: UserModel?
    ) : Call<UserModel>
}