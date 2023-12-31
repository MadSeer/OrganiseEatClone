package com.example.organiseeatclone.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.organiseeatclone.R

class IngredientsRecyclerViewAdapter(
    var ingredientsList: ArrayList<String>?,
    var deleteIngredientButtonCallback: (Int) -> Unit
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
        val deleteIngridientButton =
            holder.itemView.findViewById<ImageButton>(R.id.deleteIngredientButton)
        name.text = currentIngredient.toString()
        deleteIngridientButton.setOnClickListener { deleteIngredientButtonCallback(position) }

    }

    override fun getItemCount(): Int {
        return ingredientsList?.size ?: 0
    }

}