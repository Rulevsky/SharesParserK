package com.example.sharesparserk.SetPricesForNotificationActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sharesparserk.R
import com.example.sharesparserk.database1.SSDatabase
import com.example.sharesparserk.database1.StocksSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SetPricesForNotificationActivity : AppCompatActivity() {
    lateinit var settingsIDtextView: TextView
    lateinit var lowPriceET: EditText
    lateinit var highPriceET: EditText
    lateinit var acronymSettingsTextView: TextView
    lateinit var saveBtn: Button
    var currentPrice = 0.00
    val applicationScope = CoroutineScope(SupervisorJob())
    val stocksSettingsDatabase =
        SSDatabase.getInstance(this, applicationScope).ssDatabaseDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_settings)

        highPriceET = findViewById(R.id.highPriceET)
        settingsIDtextView = findViewById(R.id.settingsIDtextView)
        lowPriceET = findViewById(R.id.lowPriceET)
        acronymSettingsTextView = findViewById(R.id.acronymSettingsTextView)
        saveBtn = findViewById(R.id.saveBtn)

        settingsIDtextView.text = intent.getStringExtra("thisSettingsId")
        acronymSettingsTextView.text = intent.getStringExtra("thisAcronym")
        lowPriceET.setText(intent.getStringExtra("thisLowPrice"))
        highPriceET.setText(intent.getStringExtra("thisHighPrice"))
        currentPrice = intent.getStringExtra("thisCurrentPrice").toString().toDouble()

        saveBtn.setOnClickListener {
            saveToDb()
        }
    }

    private fun saveToDb() {
        var allGood = inputChecker()
        if (allGood) {
            Toast.makeText(applicationContext, "Settings Saved", Toast.LENGTH_SHORT).show()
            CoroutineScope(SupervisorJob()).launch(Dispatchers.IO) {
                stocksSettingsDatabase.update(
                    StocksSettings(
                        settingsIDtextView.text.toString().toInt(),
                        lowPriceET.text.toString().toDouble(),
                        highPriceET.text.toString().toDouble(),
                        acronymSettingsTextView.text.toString(),
                        currentPrice
                    )
                )
            }
        }
    }

    fun inputChecker(): Boolean {
        var a: Double
        var b: Double
        try {
            a = lowPriceET.text.toString().toDouble()
            b = highPriceET.text.toString().toDouble()
            return true
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Enter digits", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}