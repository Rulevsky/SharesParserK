package com.example.sharesparserk.AppSettingsActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sharesparserk.R

class AppSettingsActivity : AppCompatActivity() {

    lateinit var apiKeyEditText: EditText
    lateinit var saveAppSettingsBtn: Button
    val APP_PREFERENCES_KEY = "apikey"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_settings)

        apiKeyEditText = findViewById(R.id.apiKeyEditText)
        saveAppSettingsBtn = findViewById(R.id.saveAppSettingsBtn)
        saveAppSettingsBtn.setOnClickListener { onSaveBtn() }


    }

    private fun onSaveBtn() {
//        var key = apiKeyEditText.text.toString()
//        var appSettings = getSharedPreferences(APP_PREFERENCES_KEY, MODE_PRIVATE)
//        appSettings.edit().putString(APP_PREFERENCES_KEY, key).apply()
//        checkSharedPrefs()
        Toast.makeText(this, "coming soon",Toast.LENGTH_LONG).show()
    }

    private fun checkSharedPrefs() {
        var appSettings = getSharedPreferences(APP_PREFERENCES_KEY, MODE_PRIVATE)
        var keyFromSP = appSettings.getString(APP_PREFERENCES_KEY, "").toString()
        Log.e("sharedPrefs", keyFromSP)
    }


}