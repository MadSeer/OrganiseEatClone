package com.example.organiseeatclone.ui.addDish

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.launch
import com.example.organiseeatclone.contracts.ActivityImageContract
import com.example.organiseeatclone.core.BaseActivity
import com.example.organiseeatclone.database.DishParcelizeLocalModel
import com.example.organiseeatclone.databinding.ActivityNewDishBinding
import com.example.organiseeatclone.ui.adapters.IngredientsRecyclerViewAdapter
import com.example.organiseeatclone.ui.addDish.ChooseCategory.ChooseCategoryActivity
import org.koin.android.ext.android.inject
import org.mongodb.kbson.ObjectId

class AddDishActivity : BaseActivity<ActivityNewDishBinding>() {

    private val viewModel: AddDishViewModel by inject()
    var imgUri: Uri? = null
    var icoUri: Uri? = null
    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivityNewDishBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.ingredientsLiveData.observe(this, ::handleIngredients)
    }

    private fun handleIngredients(strings: ArrayList<String>?) {
        val adapter = IngredientsRecyclerViewAdapter(strings, ::deleteIngredientButtonCallback)
        binding.ingredientRecyclerView2.adapter = adapter
    }

    private fun deleteIngredientButtonCallback(position: Int) {
        viewModel.deleteIngredient(position)
    }

    override fun ActivityNewDishBinding.initializeLayout() {

        addIngridientButton.setOnClickListener {
            viewModel.addIngredient(newIngridient.text.toString())
            newIngridient.text = null
        }

        val imageActivity = createAddImageActivityForResult(receiptImage, URI_IMG)
        addImageButton.setOnClickListener {
            imageActivity.launch()
        }

        val iconActivity = createAddImageActivityForResult(dishIcon, URI_ICO)
        changeIconButton.setOnClickListener {
            iconActivity.launch()
        }

        saveReceiptButton.setOnClickListener {
            val intent = Intent(this@AddDishActivity, ChooseCategoryActivity::class.java)
            val model = DishParcelizeLocalModel(
                id = ObjectId().toString(),
                name = dishTitle.text.toString(),
                recipe = receipt.text.toString(),
                ingridients = viewModel.ingredientsLiveData.value,
                image = imgUri,
                icon = icoUri
            )
            intent.putExtra(DISH_MODEL, model)
            this@AddDishActivity.startActivity(intent)
        }

    }

    private fun createAddImageActivityForResult(
        imageView: ImageView,
        uriIdentify: String
    ): ActivityResultLauncher<Unit> {
        val activityLauncher = registerForActivityResult(ActivityImageContract()) { result ->
            if (result != null) {
                imageView
                    .setImageBitmap(
                        MediaStore
                            .Images
                            .Media
                            .getBitmap(
                                this@AddDishActivity
                                    .contentResolver,
                                result
                            )
                    )
            }
            if (uriIdentify == URI_ICO) imgUri = result else icoUri = result
        }
        return activityLauncher
    }

    companion object {
        const val DISH_MODEL = "Dish Model"
        const val URI_IMG = "Uri image"
        const val URI_ICO = "Uri icon"
    }
}

/*val stream = ByteArrayOutputStream()
var imgByteArray = ByteArray(0)
var icoByteArray = ByteArray(0)

if(receiptImage.drawable != null){
    var img = receiptImage.drawable.toBitmap()
    img.compress(Bitmap.CompressFormat.JPEG,100,stream)
    imgByteArray = stream.toByteArray()
}

if(dishIcon.drawable != null){
    var ico = dishIcon.drawable.toBitmap()
    ico.compress(Bitmap.CompressFormat.PNG,100,stream)
    icoByteArray = stream.toByteArray()
}*/