package com.example.test.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Agent::class], version = 9) //связывает ентити и дао
abstract class AppDatabase : RoomDatabase() {
    abstract fun agentDao(): AgentDao
    companion object {
        private var INSTANCE: AppDatabase? = null //хранение экземпляра бд

        //получение экземпляра бд
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                        ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE!!
        }
    }
}