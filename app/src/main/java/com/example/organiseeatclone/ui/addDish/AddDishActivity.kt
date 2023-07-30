package com.example.organiseeatclone.ui.addDish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.organiseeatclone.core.BaseActivity
import com.example.organiseeatclone.databinding.ActivityNewDishBinding
import com.example.organiseeatclone.ui.adapters.IngredientsRecyclerViewAdapter
import org.koin.android.ext.android.inject

class AddDishActivity : BaseActivity<ActivityNewDishBinding>() {

    val viewModel: AddDishViewModel by inject()
    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivityNewDishBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.ingredientsLiveData.observe(this, ::handleIngredients)
    }

    private fun handleIngredients(strings: ArrayList<String>?) {
        val adapter = IngredientsRecyclerViewAdapter(strings,::deleteIngredientButtonCallback)
        binding.ingredientRecyclerView2.adapter = adapter
    }

    private fun deleteIngredientButtonCallback(position: Int) {
        viewModel.deleteIngredient(position)
    }

    override fun ActivityNewDishBinding.initializeLayout() {
        addIngridientButton.setOnClickListener {
            viewModel.addIngredient(newIngridient.text.toString())
            newIngridient.text = null
        }
    }
}