package com.example.organiseeatclone.ui.addDish.ChooseCategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.organiseeatclone.core.BaseActivity
import com.example.organiseeatclone.database.DishParcelizeLocalModel
import com.example.organiseeatclone.database.DishTypeLocalModel
import com.example.organiseeatclone.databinding.ActivityNewDishCategoryBinding
import com.example.organiseeatclone.ui.adapters.DishTypeChooseRecyclerViewAdapter
import com.example.organiseeatclone.ui.addDish.AddDishActivity.Companion.DISH_MODEL
import org.koin.android.ext.android.inject

class ChooseCategoryActivity : BaseActivity<ActivityNewDishCategoryBinding>() {

    val viewModel: ChooseCategoryViewModel by inject()
    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivityNewDishCategoryBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = intent.getParcelableExtra<DishParcelizeLocalModel>(DISH_MODEL)
        viewModel.dishesTypeLiveData.observe(this, ::handleDishesType)
        viewModel.getDishTypes()
    }

    private fun handleDishesType(dishTypeLocalModels: List<DishTypeLocalModel>?) {
        val adapter = DishTypeChooseRecyclerViewAdapter(dishTypeLocalModels)
        binding.recyclerView2.adapter = adapter
    }

    override fun ActivityNewDishCategoryBinding.initializeLayout() {

    }
}