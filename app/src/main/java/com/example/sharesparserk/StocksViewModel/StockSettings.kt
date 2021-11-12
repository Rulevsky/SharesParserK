package com.example.sharesparserk.StocksViewModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.example.sharesparserk.R

class StockSettings : AppCompatActivity() {
    lateinit var settingsIDtextView: TextView
    lateinit var lowPriceET: EditText
    lateinit var highPriceET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_settings)

        val settingsID = intent.getStringExtra("thisSettingsId")
        highPriceET = findViewById(R.id.highPriceET)
        settingsIDtextView = findViewById(R.id.settingsIDtextView)
        lowPriceET = findViewById(R.id.lowPriceET)
        settingsIDtextView.text = settingsID


    }
}