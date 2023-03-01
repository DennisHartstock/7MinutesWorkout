package com.commcode.a7minutesworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.commcode.a7minutesworkout.databinding.ItemHistoryRowBinding

class HistoryAdapter(private val historyItems: ArrayList<String>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemHistoryRowBinding) : RecyclerView.ViewHolder(binding.root) {
        val llHistoryItem = binding.llHistoryItem
        val tvPosition = binding.tvPosition
        val tvItem = binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHistoryRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return historyItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = historyItems[position]
        (position + 1).also { holder.tvPosition.text = it.toString() }
        holder.tvItem.text = date

        if (position % 2 == 0) {
            holder.llHistoryItem.setBackgroundColor(Color.parseColor("#AAAAAA"))
        } else {
            holder.llHistoryItem.setBackgroundColor(Color.WHITE)
        }
    }
}