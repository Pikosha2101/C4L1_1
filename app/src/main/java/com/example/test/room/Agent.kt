package com.example.test.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "Agent")
data class Agent(
    @PrimaryKey(autoGenerate = true)
    val agentID: Int,
    val email: String,
    val login: String,
    val password: String
)
