package com.example.organiseeatclone.ui.addDish.ChooseCategory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.organiseeatclone.database.Database
import com.example.organiseeatclone.database.DishParcelizeLocalModel
import com.example.organiseeatclone.database.DishTypeLocalModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ChooseCategoryViewModel() : ViewModel(), KoinComponent {

    val database: Database by inject()
    val dishesTypeLiveData = MutableLiveData<List<DishTypeLocalModel>>()
    var dishTypeSelected = mutableListOf<DishTypeLocalModel>()
    //var saveStateLiveData = MutableLiveData<String>(CATEGORY_CHOOSE) //Состояние

    fun getDishTypes() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = database.getDishTypes()
            dishesTypeLiveData.postValue(list)
        }
    }

    fun chosedDishTypesListAction(dishTypeLocalModel: DishTypeLocalModel) {
        if (!dishTypeSelected.contains(dishTypeLocalModel))
            dishTypeSelected.add(dishTypeLocalModel) else
            dishTypeSelected.remove(dishTypeLocalModel)
    }

    /*suspend fun convertUriToByteArray(contentResolver: ContentResolver,uri:Uri): ByteArray? {
        return withContext(Dispatchers.IO) {
            try {
                val inputStream: InputStream? = contentResolver.openInputStream(uri)
                val byteArrayOutputStream = ByteArrayOutputStream()
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (inputStream?.read(buffer).also { bytesRead = it!! } != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead)
                }
                byteArrayOutputStream.close()
                inputStream?.close()
                byteArrayOutputStream.toByteArray()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }*/

    fun saveReceipt(dish: DishParcelizeLocalModel) {

        database.addDish(
            dishTypeSelected.toList(),
            dish.name,
            dish.recipe,
            dish.ingridients ?: listOf(),
            dish.image,
            dish.icon
        )

    }

    companion object {
        const val CATEGORY_CHOOSE = "Category choose"
        const val SAVING_RECEIPT = "Saving receipt"
    }

}