package com.example.sharesparserk.database

import android.content.Context
import android.util.Log
import androidx.room.Database

import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Stock::class], version = 1, exportSchema = false)
abstract class StocksDatabase : RoomDatabase() {
    abstract val stocksDatabaseDao: StocksDatabaseDao

    companion object {
        @Volatile
        private var INSTANSE: StocksDatabase? = null
        fun getInstance(context: Context): StocksDatabase {
            Log.e("get_instanse", "1")
            synchronized(this) {
                Log.e("get_instanse", "2")
                var instance = INSTANSE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StocksDatabase::class.java,
                        "stocks_price_table"

                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    Log.e("get_instanse", "3")
                    INSTANSE = instance
                }
                Log.e("get_instanse", "4")
                return instance
            }
        }
    }
}