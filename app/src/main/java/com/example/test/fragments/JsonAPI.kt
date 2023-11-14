package com.example.test.fragments

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface JsonAPI {
    @POST("user/register")
    suspend fun registrationByPass(@Body userModel: UserModel?) : UserModel
}