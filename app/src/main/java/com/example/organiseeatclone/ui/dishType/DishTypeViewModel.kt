package com.example.organiseeatclone.ui.dishType

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.organiseeatclone.database.Database
import com.example.organiseeatclone.database.Dish
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DishTypeViewModel: ViewModel(), KoinComponent {

    val database: Database by inject()
    val dishesLiveData = MutableLiveData<List<Dish>>()

    fun getDishes(dishType: String){
        CoroutineScope(Dispatchers.IO).launch {
            val dishes = database.getDishesByType(dishType)
            dishesLiveData.postValue(dishes)
        }
    }

}