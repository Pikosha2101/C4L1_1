package com.example.test.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "CoworkingService",
    foreignKeys = [
    ForeignKey(
        entity = Cowork::class,
        parentColumns = ["coworkingID"],
        childColumns = ["coworkingFK"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = Service::class,
        parentColumns = ["serviceID"],
        childColumns = ["serviceFK"],
        onDelete = ForeignKey.CASCADE
        )
    ])
data class CoworkingService(
    @PrimaryKey(autoGenerate = true)
    val coworkingServiceID: Int,
    val coworkingFK: Int,
    val serviceFK: Int,
    val price: Double,
    val bookingAvailable: Boolean
)
