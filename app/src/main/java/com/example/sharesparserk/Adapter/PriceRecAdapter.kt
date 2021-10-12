package com.example.sharesparserk.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sharesparserk.R
import com.example.sharesparserk.model.OneStockPosition

class PriceRecAdapter(private val dataSet: MutableList<OneStockPosition>): RecyclerView.Adapter<PriceRecAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        init {
            textView = view.findViewById(R.id.acronymTextView)
            // добавить остальные
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceRecAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_share_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PriceRecAdapter.ViewHolder, position: Int) {
        holder.textView.text = dataSet.get(position).acronym
    }

    override fun getItemCount() = dataSet.size


}