package com.example.test.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AgentDao {
    @Insert
    suspend fun insert(agent: Agent)

    @Update
    suspend fun update(agent: Agent)

    @Query("SELECT * FROM Agent")
    suspend fun getAllAgents(): List<Agent>;

    @Query("SELECT * FROM Agent WHERE agentID = :id")
    suspend fun getAgentById(id: Int): Agent

    @Query("SELECT COUNT(*) FROM Agent ")
    suspend fun getAgentCount(): Int

    @Query("SELECT COUNT(*) FROM Agent where login = :log and password = :pas")
    suspend fun getAgentCheck(log: String, pas: String): Int

    @Query("SELECT COUNT(*) FROM Agent where agentID = :id")
    suspend fun getAgentCheckId(id: Int): Int

    @Query("Delete FROM Agent where agentID = :id")
    suspend fun deleteAgent(id: Int): Int
}