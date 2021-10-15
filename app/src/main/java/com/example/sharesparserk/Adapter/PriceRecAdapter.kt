package com.example.sharesparserk.Adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.sharesparserk.PricesActivity
import com.example.sharesparserk.R
import com.example.sharesparserk.database.SettingsDatabase
import com.example.sharesparserk.database.SettingsForStocks
import com.example.sharesparserk.model.OneStockPosition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PriceRecAdapter(private val dataSet: MutableList<OneStockPosition>): RecyclerView.Adapter<PriceRecAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val acronymTextView: TextView
        val currentPriceTextView: TextView
        val lowPriceEditText: EditText
        val highPriceEditText: EditText
        init {
            acronymTextView = view.findViewById(R.id.acronymTextView)
            currentPriceTextView = view.findViewById(R.id.currentPriceTextView)
            lowPriceEditText = view.findViewById(R.id.lowPriceEditText)
            highPriceEditText = view.findViewById(R.id.highPriceEditText)

            lowPriceEditText.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    Log.e("before text changed", "TODO(\"Not yet implemented\")")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    view
                    GlobalScope.launch(Dispatchers.IO) {

                    }
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceRecAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_share_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PriceRecAdapter.ViewHolder, position: Int) {
        holder.acronymTextView.text = dataSet.get(position).acronym
        holder.currentPriceTextView.text = dataSet.get(position).currentPrice.toString()

    }

    override fun getItemCount() = dataSet.size



}