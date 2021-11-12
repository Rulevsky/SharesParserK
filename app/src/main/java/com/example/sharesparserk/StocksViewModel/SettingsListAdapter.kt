package com.example.sharesparserk.StocksViewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sharesparserk.R
import com.example.sharesparserk.database.SettingsForStocks

class SettingsListAdapter :
    ListAdapter<SettingsForStocks, SettingsListAdapter.SettingsViewHolder>(SettingsComparator()) {
    var application = Application()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        return SettingsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val current = getItem(position)

        holder.idSetTextView.text = current.settingsID.toString()
        holder.lowPriceSetTextView.text = current.lowPrice.toString()
        holder.highPriceSetTextView.text = current.highPrice.toString()

        holder.itemView.setOnClickListener{
            view -> position
            var thisSettingsId: Int = position + 1
            var intent: Intent = Intent(view.context, StockSettings::class.java)
            intent.putExtra("thisSettingsId", thisSettingsId.toString())

            view.context.startActivity(intent)
            Log.e("tag", "clicked position" + position.toString())
        }
    }


    class SettingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idSetTextView: TextView = itemView.findViewById(R.id.idSetTextView)
        val lowPriceSetTextView: TextView = itemView.findViewById(R.id.lowPriceSetTextView)
        val highPriceSetTextView: TextView = itemView.findViewById(R.id.highPriceSetTextView)

        companion object {
            fun create(parent: ViewGroup): SettingsViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.settings_holder, parent, false)
                return SettingsViewHolder(view)
            }
        }



    }


    class SettingsComparator : DiffUtil.ItemCallback<SettingsForStocks>() {
        override fun areItemsTheSame(
            oldItem: SettingsForStocks,
            newItem: SettingsForStocks
        ): Boolean {
            return oldItem.settingsID == newItem.settingsID
        }

        // check how work if work
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: SettingsForStocks,
            newItem: SettingsForStocks
        ): Boolean {
            return oldItem == newItem
        }
    }


}

