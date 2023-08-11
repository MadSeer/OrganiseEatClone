package com.example.organiseeatclone.ui.dishType

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.organiseeatclone.core.BaseActivity
import com.example.organiseeatclone.database.Dish
import com.example.organiseeatclone.databinding.ActivityDishTypeBinding
import com.example.organiseeatclone.ui.adapters.DishesRecyclerViewAdapter
import com.example.organiseeatclone.ui.main.MainActivity.Companion.DISH_TYPE_NAME
import org.koin.android.ext.android.inject

class DishTypeActivity : BaseActivity<ActivityDishTypeBinding>() {
    val viewModel: DishTypeViewModel by inject()
    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivityDishTypeBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.dishesLiveData.observe(this,::handleDishes)
        viewModel.getDishes(intent.getStringExtra(DISH_TYPE_NAME).toString())
    }

    private fun handleDishes(dishes: List<Dish>?) {
        val adapter = DishesRecyclerViewAdapter(dishes)
        binding.dishesRecyclerView.adapter = adapter
    }

    override fun ActivityDishTypeBinding.initializeLayout() {
        val dishType = intent.getBundleExtra(DISH_TYPE_NAME)

    }
}