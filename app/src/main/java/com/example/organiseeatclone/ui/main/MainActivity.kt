package com.example.organiseeatclone.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.organiseeatclone.core.BaseActivity
import com.example.organiseeatclone.database.DishTypeLocalModel
import com.example.organiseeatclone.databinding.ActivityMainBinding
import com.example.organiseeatclone.ui.adapters.DishTypeRecyclerViewAdapter
import com.example.organiseeatclone.ui.addDish.AddDishActivity
import com.example.organiseeatclone.ui.dishType.DishTypeActivity
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.dishesTypeLiveData.observe(this, ::handleDishesType)
        viewModel.getDishTypes()
    }

    private fun handleDishesType(dishTypeLocalModels: List<DishTypeLocalModel>?) {
        val adapter = DishTypeRecyclerViewAdapter(dishTypeLocalModels,::categoryChooseListenerCallback)
        binding.recyclerView.adapter = adapter
    }

    private fun categoryChooseListenerCallback(dishType: String) {
        val intent = Intent(this@MainActivity,DishTypeActivity::class.java)
        intent.putExtra(DISH_TYPE_NAME,dishType)
    }

    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivityMainBinding.inflate(inflater, container, false)


    override fun ActivityMainBinding.initializeLayout() {
        addRecipeButton.setOnClickListener{
            val intent = Intent(this@MainActivity,AddDishActivity::class.java)
            this@MainActivity.startActivity(intent)
        }
    }

    companion object{
        const val DISH_TYPE_NAME = "Dish type name"
    }
}