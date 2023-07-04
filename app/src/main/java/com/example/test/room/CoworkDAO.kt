package com.example.test.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoworkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCowork(cowork: Cowork)

    @Query("SELECT * FROM Cowork WHERE coworkingID = :id")
    suspend fun getCoworkById(id: Int): Cowork

    @Query("SELECT COUNT(*) FROM Cowork where coworkingID = :id")
    suspend fun getCoworkCheckId(id: Int): Int
}