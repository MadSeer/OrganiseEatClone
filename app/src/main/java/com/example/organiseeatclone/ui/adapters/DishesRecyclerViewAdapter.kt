package com.example.organiseeatclone.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.organiseeatclone.R
import com.example.organiseeatclone.database.Dish

class DishesRecyclerViewAdapter(
    private val dishes: List<Dish>?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dish, parent, false)
        return ViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return dishes?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentDish = dishes?.get(position)
        var icon = holder.itemView.findViewById<ImageView>(R.id.dishTitleDish)
        var title = holder.itemView.findViewById<TextView>(R.id.dishTitleDish)
        if (currentDish?.icon != null) icon.setImageURI(currentDish?.icon?.toUri())
        else icon.setImageDrawable(R.drawable.icon_typedish_any.toDrawable())
        title.text = currentDish?.name
    }
}