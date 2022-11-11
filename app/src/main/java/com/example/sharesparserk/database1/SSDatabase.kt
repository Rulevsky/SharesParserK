package com.example.sharesparserk.database1

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [StocksSettings::class], version = 1, exportSchema = false)
abstract class SSDatabase : RoomDatabase() {

    abstract fun ssDatabaseDao(): SSDatabaseDao
    companion object {
        @Volatile
        private var INSTANCE: SSDatabase? = null
        fun getInstance(
            context: Context,
            scope: CoroutineScope
        ): SSDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SSDatabase::class.java,
                        "settings_table"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(SSDatabaseCallback(scope))
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        private class SSDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.ssDatabaseDao())
                    }
                }
            }

            suspend fun populateDatabase(ssDatabaseDao: SSDatabaseDao) {
                var i: Int = 1
                while (i < 33) {
                    var stocksSettings = StocksSettings(i, 1.11, 9999.9, "ACRONYM", 555.5)
                    ssDatabaseDao.insert(stocksSettings)
                    Log.e("settdb", "start polulate" + i.toString())
                    i++
                }
            }
        }
    }
}