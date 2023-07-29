package com.example.organiseeatclone.ui.addDish

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

class AddDishViewModel() : ViewModel(), KoinComponent {

    var ingredientsLiveData = MutableLiveData<ArrayList<String>?>()

    fun addIngredient(ingredient: String) {
        var currentList = ingredientsLiveData.value
        if (currentList == null) {
            currentList = arrayListOf()
        }
        currentList.add(ingredient)
        ingredientsLiveData.value = currentList
    }

}