package com.example.test.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Event",
    foreignKeys = [
    ForeignKey(
        entity = Cowork::class,
        parentColumns = ["coworkingID"],
        childColumns = ["coworkingFK"],
        onDelete = ForeignKey.CASCADE
        )]
    )
data class Event(
    @PrimaryKey(autoGenerate = true)
    val eventID: Int,
    val name: String,
    val numberofplaces: Int,
    val requirements: String,
    val coworkingFK: Int,
    val description: String,
    val shortDescription: String,
    val pointsValue: Int
)