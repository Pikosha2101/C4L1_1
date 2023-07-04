package com.example.test.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ServiceDao {
    @Insert

    suspend fun insert(service: Service)
    @Update
    suspend fun update(service: Service)

    @Delete
    suspend fun delete(service: Service)

    @Query("SELECT * FROM Service")
    suspend fun getAllServices(): List<Service>

    @Query("SELECT * FROM Service WHERE serviceID = :id")
    suspend fun getServiceById(id: Int): Service
}