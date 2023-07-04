package com.example.test.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Service")
data class Service(
    @PrimaryKey(autoGenerate = true)
    val serviceID: Int,
    val name: String,
    val description: String
)