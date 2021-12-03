package com.example.sharesparserk.StocksViewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sharesparserk.R
import com.example.sharesparserk.SetPricesForNotificationActivity.SetPricesForNotificationActivity
import com.example.sharesparserk.database1.StocksSettings

class StocksSettingsAdapter :
    ListAdapter<StocksSettings, StocksSettingsAdapter.SettingsViewHolder>(StocksSettingsComparator()) {
    //var application = Application()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        return SettingsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val current = getItem(position)

        holder.idSetTextView.text = current.settingsID.toString()
        holder.lowPriceSetTextView.text = current.lowPrice.toString()
        holder.highPriceSetTextView.text = current.highPrice.toString()
        holder.acronymTextView.text = current.acronym
        holder.currentPriceTextView.text = current.currentPrice.toString() // + application.getString(R.string.dollar_sign)

        holder.itemView.setOnClickListener{
            view -> position
            val thisSettingsId: Int = position + 1
            var intent: Intent = Intent(view.context, SetPricesForNotificationActivity::class.java)
            intent.putExtra("thisSettingsId", thisSettingsId.toString())
            intent.putExtra("thisAcronym", current.acronym)
            intent.putExtra("thisLowPrice", current.lowPrice.toString())
            intent.putExtra("thisHighPrice", current.highPrice.toString())
            intent.putExtra("thisCurrentPrice", current.currentPrice.toString())
            view.context.startActivity(intent)
            Log.e("tag", "clicked position" + position.toString())
        }
    }

    class SettingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idSetTextView: TextView = itemView.findViewById(R.id.settingsIdTextView)
        val lowPriceSetTextView: TextView = itemView.findViewById(R.id.lowPriceTextView)
        val highPriceSetTextView: TextView = itemView.findViewById(R.id.highPriceTextView)
        val acronymTextView: TextView = itemView.findViewById(R.id.acronymTextView)
        val currentPriceTextView: TextView = itemView.findViewById(R.id.currentPriceTextView)

        companion object {
            fun create(parent: ViewGroup): SettingsViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.stocks_settings_holder, parent, false)
                return SettingsViewHolder(view)
            }
        }
    }

    class StocksSettingsComparator : DiffUtil.ItemCallback<StocksSettings>() {
        override fun areItemsTheSame(
            oldItem: StocksSettings,
            newItem: StocksSettings
        ): Boolean {
            return oldItem.settingsID == newItem.settingsID
        }

        // check how it works
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: StocksSettings,
            newItem: StocksSettings
        ): Boolean {
            return oldItem == newItem
        }
    }
}

