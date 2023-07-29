package com.example.organiseeatclone.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.organiseeatclone.R

class IngredientsRecyclerViewAdapter(
    var ingredientsList: ArrayList<String>?
) : RecyclerView.Adapter<ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ingredient, parent, false)
        return ViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentIngredient = ingredientsList?.get(position)
        val name = holder.itemView.findViewById<TextView>(R.id.ingredientTitleInList)
        //val button = holder.itemView.findViewById<Button>(R.id.deleteDishButton)
        name.text = currentIngredient.toString()
    }

    override fun getItemCount(): Int {
        return ingredientsList?.size ?: 0
    }

}