package com.example.organiseeatclone.ui.dishType

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.organiseeatclone.core.BaseActivity
import com.example.organiseeatclone.databinding.ActivityDishTypeBinding
import com.example.organiseeatclone.ui.main.MainActivity.Companion.DISH_TYPE_NAME

class DishTypeActivity : BaseActivity<ActivityDishTypeBinding>() {
    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivityDishTypeBinding.inflate(inflater, container, false)

    override fun ActivityDishTypeBinding.initializeLayout() {
        val dishType = intent.getBundleExtra(DISH_TYPE_NAME)

    }
}