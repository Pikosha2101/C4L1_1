package com.example.test.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CoworkingServiceDao {
    @Insert
    suspend fun insert(coworkingService: CoworkingService)

    @Update
    suspend fun update(coworkingService: CoworkingService)

    @Delete
    suspend fun delete(coworkingService: CoworkingService)

    @Query("SELECT * FROM CoworkingService")
    suspend fun getAllCoworkingServices(): List<CoworkingService>;

    @Query("SELECT * FROM CoworkingService WHERE coworkingServiceID = :id")
    suspend fun getCoworkingServiceById(id: Int): CoworkingService
}