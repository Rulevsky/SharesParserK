package com.example.sharesparserk.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [SettingsForStocks::class], version = 1, exportSchema = false)
abstract class SettingsDatabase : RoomDatabase() {

    abstract fun settingsDatabaseDao(): SettingsDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: SettingsDatabase? = null
        fun getInstance(
            context: Context,
            scope: CoroutineScope
            ): SettingsDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SettingsDatabase::class.java,
                        "settings_table"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(SettingsDatabaseCallback(scope))
                        .build()
                    INSTANCE = instance
                }
                return instance
            }


        }

        private class SettingsDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.settingsDatabaseDao())
                    }
                }
            }

            suspend fun populateDatabase( settingsDatabaseDao: SettingsDatabaseDao){
                var i: Int = 1
                while (i < 33) {
                    var settingsForStocks = SettingsForStocks(i, 1.11, 999.99)
                    settingsDatabaseDao.insert(settingsForStocks)
                    i++
                    Log.e("settdb", "start polulate" + i.toString())
                }
            }
        }


//                settingsDatabaseDao.insert(SettingsForStocks(1, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(2, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(3, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(4, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(5, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(6, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(7, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(8, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(9, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(10, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(11, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(12, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(13, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(14, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(15, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(16, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(17, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(18, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(19, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(20, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(21, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(22, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(23, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(24, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(25, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(26, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(27, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(28, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(29, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(30, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(31, 1.11, 999.99))
//                settingsDatabaseDao.insert(SettingsForStocks(32, 1.11, 999.99))


    }
}

