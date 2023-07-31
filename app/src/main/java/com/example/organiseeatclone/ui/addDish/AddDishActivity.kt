package com.example.organiseeatclone.ui.addDish

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.organiseeatclone.core.BaseActivity
import com.example.organiseeatclone.databinding.ActivityNewDishBinding
import com.example.organiseeatclone.ui.adapters.IngredientsRecyclerViewAdapter
import org.koin.android.ext.android.inject

class AddDishActivity : BaseActivity<ActivityNewDishBinding>() {

    private val viewModel: AddDishViewModel by inject()
    private val imgGetLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK){
            binding.receiptImage.setImageBitmap(MediaStore.Images.Media.getBitmap(this.contentResolver,result.data!!.data))
        }
    }
    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivityNewDishBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.ingredientsLiveData.observe(this, ::handleIngredients)
    }

    private fun handleIngredients(strings: ArrayList<String>?) {
        val adapter = IngredientsRecyclerViewAdapter(strings,::deleteIngredientButtonCallback)
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
        addImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            imgGetLauncher.launch(intent)
        }
    }
}