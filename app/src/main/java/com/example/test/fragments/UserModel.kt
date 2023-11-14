package com.example.test.fragments

data class UserModel (
    val email: String,
    val firstname: String,
    val lastname: String,
    val password: String? = null,
    val patronymic: String,
    val phone: String? = null
)