package com.example.sharesparserk.StocksViewModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sharesparserk.R
import com.example.sharesparserk.SharesApplication

class StocksActivity : AppCompatActivity() {
    private val settingsViewModel: SettingsViewModel by viewModels {
        SettingsViewModelFactory((application as SharesApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stocks)

        val recyclerView = findViewById<RecyclerView>(R.id.newRecView)
        val adapter = StocksSettingsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)



        settingsViewModel.allStocksSettingsRepository.observe(this, Observer { stocksSettings ->
            stocksSettings?.let { adapter.submitList(it)}
        })



    }
}