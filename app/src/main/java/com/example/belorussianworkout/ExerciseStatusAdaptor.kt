package com.example.belorussianworkout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ExerciseStatusAdaptor (val items: ArrayList<ExerciseModule>, val context: Context) : RecyclerView.Adapter<ExerciseStatusAdaptor.ViewHolder> () {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem = view.findViewById<TextView>(R.id.tvItem)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(
                    R.layout.item_exercise_status,
                    parent,
                    false
                )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val module: ExerciseModule = items[position]

        holder.tvItem.text = module.getId().toString()

        if (module.getIsSelected()) {
            holder.tvItem.background =
                ContextCompat.getDrawable(context, R.drawable.item_circular_thin_color_accent)
            holder.tvItem.setTextColor(Color.parseColor("#212121"))
        } else if (module.getIsCompleted()) {
            holder.tvItem.background =
                ContextCompat.getDrawable(context, R.drawable.item_circular_color_accent_background)
            holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
        } else {
            holder.tvItem.background =
                ContextCompat.getDrawable(context, R.drawable.item_circular_color_gray_background)
            holder.tvItem.setTextColor(Color.parseColor("#212121"))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}


