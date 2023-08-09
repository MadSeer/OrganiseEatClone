package com.example.organiseeatclone.ui.dishType

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.organiseeatclone.database.Database
import com.example.organiseeatclone.database.Dish
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DishTypeViewModel: ViewModel(), KoinComponent {

    val database: Database by inject()
    val dishes = MutableLiveData<List<Dish>>()

    fun getDishes(){

    }
}