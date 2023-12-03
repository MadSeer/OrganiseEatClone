package com.example.organiseeatclone.ui.addDish.ChooseCategory

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.organiseeatclone.database.Database
import com.example.organiseeatclone.database.DishParcelizeLocalModel
import com.example.organiseeatclone.database.DishTypeLocalModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File

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

    private fun getTmpImageFileFromUri(
        context: Context,
        uri: Uri,
        fileSuffix: String = ".jpg",
        filePrefix: String = "logo_"
    ): File {
        val file = File.createTempFile(filePrefix, fileSuffix)
        file.deleteOnExit()
        context.contentResolver.openInputStream(uri)?.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        return file
    }

    private fun getImg(file: File): Bitmap? {
        return BitmapFactory.decodeFile(file.toString())
    }

    fun post(model:DishParcelizeLocalModel, context: Context){

        /*val image = getTmpImageFileFromUri(
            context,
            model.image!!
        )

        val imgBitmap = getImg(image).toString()

        val icon = getTmpImageFileFromUri(
            context,
            model.icon!!
        )

        val iconBitmap = getImg(icon).toString();*/

        /*val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(tmp.extension)
            .orEmpty()
        val request = tmp.asRequestBody(mimeType.toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("image", tmp.name, request)*/

            CoroutineScope(Dispatchers.IO).launch {
                val client = OkHttpClient()

                val formBody = FormBody.Builder()
                    .add("id", model.id)
                    .add("name", model.name)
                    .add("recipe", model.recipe)
                    .add("ingridients", model.ingridients.toString())
                    .add("image", model.image.toString())
                    .add("icon", model.icon.toString())
                    .build();

                val request = Request.Builder()
                    .url("https://eodnc0ppu6e1uii.m.pipedream.net")
                    .post(formBody)
                    .build();

                val call = client.newCall(request)
                call.execute()
            }

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