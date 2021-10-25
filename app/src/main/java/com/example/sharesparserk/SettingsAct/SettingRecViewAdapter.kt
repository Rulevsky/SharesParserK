package com.example.sharesparserk.SettingsAct

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sharesparserk.R
import com.example.sharesparserk.database.SettingsForStocks

class SettingRecViewAdapter(private var settingsDatabaseDao: MutableList<SettingsForStocks>):
    RecyclerView.Adapter<SettingRecViewAdapter.ViewHolder>(){




    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idSetTextView: TextView
        val lowPriceSetTextView: TextView
        val highPriceSetTextView: TextView
        init{
            idSetTextView = view.findViewById(R.id.idSetTextView)
            lowPriceSetTextView = view.findViewById(R.id.lowPriceSetTextView)
            highPriceSetTextView = view.findViewById(R.id.highPriceSetTextView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.settings_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idSetTextView.text = settingsDatabaseDao.get(position)?.settingsID.toString()
        holder.lowPriceSetTextView.text = settingsDatabaseDao.get(position)?.lowPrice.toString()
        holder.highPriceSetTextView.text = settingsDatabaseDao.get(position)?.highPrice.toString()

        holder.itemView.setOnClickListener{ onItemClicked(position)}

    }

    private fun onItemClicked(position: Int) {
        Log.e("try onclick", position.toString())
    }

    override fun getItemCount() = 32
}