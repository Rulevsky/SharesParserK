package com.example.sharesparserk.database

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SettingsForStocks::class], version = 1, exportSchema = false)
abstract class SettingsDatabase: RoomDatabase() {
    abstract val settingsDatabaseDao: SettingsDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: SettingsDatabase? = null
        fun getInstance(context: Context): SettingsDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SettingsDatabase::class.java,
                        "settings_table"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}