package com.example.sharesparserk.SettingsAct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sharesparserk.R
import com.example.sharesparserk.database.SettingsDatabase
import com.example.sharesparserk.database.SettingsForStocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SettingsActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var stockIdEditText: EditText
    lateinit var lowPriceEditText: EditText
    lateinit var highPriceEditText: EditText
//    var settingsDatabaseDao: SettingsDatabaseDao? = null
    var dataset: MutableList<SettingsForStocks> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        initOrUpdateDb()
        stockIdEditText = findViewById<EditText>(R.id.stockIdEditText)
        lowPriceEditText = findViewById<EditText>(R.id.lowPriceEditText)
        highPriceEditText = findViewById<EditText>(R.id.highPriceEditText)
        lifecycleScope.launch(Dispatchers.IO) {
           var settingsDatabaseDao = SettingsDatabase.getInstance(applicationContext).settingsDatabaseDao
           var i:Int = 1
            while (i <= 32){
                settingsDatabaseDao.get(i)?.let { dataset.add(it) }
                i++
            }
        }

        recyclerView = findViewById(R.id.settingsRecView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = SettingRecViewAdapter(dataset)


    }


    suspend fun getDB() {
        var db = SettingsDatabase.getInstance(applicationContext).settingsDatabaseDao
        var settingsForStocks: SettingsForStocks? = db.get(1)

    }


    fun initOrUpdateDb() {
        //db start from 1(not from 0)
        var db = SettingsDatabase.getInstance(applicationContext).settingsDatabaseDao
        GlobalScope.launch(Dispatchers.IO) {
            var i: Int = 1
            while (i <= 32) {
                if (db.get(i)?.settingsID == null) {
                    db.insert(SettingsForStocks(i, 1.11, 999.99))
                    Log.e("settings activity db inserted", i.toString())
                }
                i++
            }
        }
    }

    fun updateDb(setId: Int, lowPrice: Double, highPrice: Double){
        GlobalScope.launch(Dispatchers.IO){
            var settingsDatabase = SettingsDatabase.getInstance(applicationContext).settingsDatabaseDao
            settingsDatabase.update(SettingsForStocks(setId, lowPrice, highPrice))
        }
    }

    fun onSaveBtnClick(view: android.view.View) {
        var setId: Int = stockIdEditText.text.toString().toInt()
        var lowPrice = lowPriceEditText.text.toString().toDouble()
        var highPrice = highPriceEditText.text.toString().toDouble()
        updateDb(setId, lowPrice, highPrice)
    }
}
