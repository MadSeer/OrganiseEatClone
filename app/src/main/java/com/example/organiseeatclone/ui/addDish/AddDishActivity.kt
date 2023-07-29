package com.example.organiseeatclone.ui.addDish

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.organiseeatclone.core.BaseActivity
import com.example.organiseeatclone.databinding.ActivityNewDishBinding

class AddDishActivity:BaseActivity<ActivityNewDishBinding>() {
    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivityNewDishBinding.inflate(inflater,container,false)

    override fun ActivityNewDishBinding.initializeLayout() {

    }
}