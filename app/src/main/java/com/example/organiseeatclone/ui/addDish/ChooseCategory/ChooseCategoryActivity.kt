package com.example.organiseeatclone.ui.addDish.ChooseCategory

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.organiseeatclone.core.BaseActivity
import com.example.organiseeatclone.database.DishParcelizeLocalModel
import com.example.organiseeatclone.database.DishTypeLocalModel
import com.example.organiseeatclone.databinding.ActivityNewDishCategoryBinding
import com.example.organiseeatclone.ui.adapters.DishTypeChooseRecyclerViewAdapter
import com.example.organiseeatclone.ui.addDish.AddDishActivity.Companion.DISH_MODEL
import com.example.organiseeatclone.ui.main.MainActivity
import org.koin.android.ext.android.inject

class ChooseCategoryActivity : BaseActivity<ActivityNewDishCategoryBinding>() {

    val viewModel: ChooseCategoryViewModel by inject()
    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivityNewDishCategoryBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.dishesTypeLiveData.observe(this, ::handleDishesType)
        viewModel.getDishTypes()
    }

    private fun handleDishesType(dishTypeLocalModels: List<DishTypeLocalModel>?) {
        val adapter = DishTypeChooseRecyclerViewAdapter(dishTypeLocalModels,::dishTypeChoose)
        binding.recyclerView2.adapter = adapter
    }

    private fun dishTypeChoose(dishTypeLocalModel: DishTypeLocalModel) {
        viewModel.chosedDishTypesListAction(dishTypeLocalModel)
    }

    override fun ActivityNewDishCategoryBinding.initializeLayout() {
        val model = intent.getParcelableExtra<DishParcelizeLocalModel>(DISH_MODEL)
        saveReceiptButton2.setOnClickListener {
            viewModel.saveReceipt(model!!)
            val intent = Intent(this@ChooseCategoryActivity,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            this@ChooseCategoryActivity.startActivity(intent)
            this@ChooseCategoryActivity.finish()
        }
    }
}