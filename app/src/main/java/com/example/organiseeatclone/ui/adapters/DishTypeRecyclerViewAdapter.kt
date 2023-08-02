package com.example.organiseeatclone.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.organiseeatclone.R
import com.example.organiseeatclone.database.DishTypeLocalModel

class DishTypeRecyclerViewAdapter(
    val dishTypes: List<DishTypeLocalModel>?
) : RecyclerView.Adapter<ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dish_type_layout, parent, false)
        return ViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentDishType = dishTypes?.get(position)
        val type = holder.itemView.findViewById<TextView>(R.id.textView)
        val icon = holder.itemView.findViewById<ImageView>(R.id.imageView)
        type.text = currentDishType?.name
        currentDishType?.let { icon.setImageResource(it.icon) }
    }

    override fun getItemCount(): Int {
        return dishTypes?.size ?: 0
    }
}