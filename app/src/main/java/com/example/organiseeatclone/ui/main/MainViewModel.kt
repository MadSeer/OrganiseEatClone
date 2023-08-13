package com.example.organiseeatclone.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.organiseeatclone.AppPreferences
import com.example.organiseeatclone.database.Database
import com.example.organiseeatclone.database.DishTypeLocalModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel() : ViewModel(), KoinComponent {

    val database: Database by inject()
    val sharedPreferences: AppPreferences by inject()
    val dishesTypeLiveData = MutableLiveData<List<DishTypeLocalModel>>()

    fun getDishTypes() {
        CoroutineScope(Dispatchers.IO).launch {
            if (sharedPreferences.isFirstRun()) {
                database.setDefaultDishTypes()
                sharedPreferences.setAnyRun()
            }
            val list = database.getDishTypes()
            dishesTypeLiveData.postValue(list)
        }
    }

    fun permissionAccess(){

    }


}