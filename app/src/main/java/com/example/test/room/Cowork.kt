package com.example.test.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cowork")
data class Cowork(
    @PrimaryKey(autoGenerate = true)
    val coworkingID: Int = 0,
    val address: String,
    val phone: String,
    val email: String
)