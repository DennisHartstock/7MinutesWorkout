package com.commcode.a7minutesworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.commcode.a7minutesworkout.databinding.ItemExerciseBinding

class ExerciseAdapter(private val exercises: ArrayList<Exercise>) :
    RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemExerciseBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvItem = binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemExerciseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItem.text = exercises[position].id.toString()

        when {
            exercises[position].isSelected -> {
                holder.tvItem.background =
                    ContextCompat.getDrawable(holder.tvItem.context, R.drawable.item_selected_bg)
            }
            exercises[position].isCompleted -> {
                holder.tvItem.background =
                    ContextCompat.getDrawable(holder.tvItem.context, R.drawable.item_completed_bg)
            }
            else -> {
                holder.tvItem.background =
                    ContextCompat.getDrawable(holder.tvItem.context, R.drawable.item_bg)
            }
        }
    }
}