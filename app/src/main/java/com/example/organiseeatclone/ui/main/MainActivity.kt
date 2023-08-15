package com.example.organiseeatclone.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.organiseeatclone.core.BaseActivity
import com.example.organiseeatclone.database.DishTypeLocalModel
import com.example.organiseeatclone.databinding.ActivityMainBinding
import com.example.organiseeatclone.ui.adapters.DishTypeRecyclerViewAdapter
import com.example.organiseeatclone.ui.addDish.AddDishActivity
import com.example.organiseeatclone.ui.dishType.DishTypeActivity
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.dishesTypeLiveData.observe(this, ::handleDishesType)
        viewModel.getDishTypes()
    }

    private fun handleDishesType(dishTypeLocalModels: List<DishTypeLocalModel>?) {
        val adapter =
            DishTypeRecyclerViewAdapter(dishTypeLocalModels, ::categoryChooseListenerCallback)
        binding.recyclerView.adapter = adapter
    }

    private fun categoryChooseListenerCallback(dishType: String) {
        if(checkPermission()) {
            startDishTypeActivity(dishType)
        }
    }

    private fun startDishTypeActivity(dishType: String) {
        val intent = Intent(this@MainActivity, DishTypeActivity::class.java)
        intent.putExtra(DISH_TYPE_NAME, dishType)
        //intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        this@MainActivity.startActivity(intent)
    }

    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivityMainBinding.inflate(inflater, container, false)


    override fun ActivityMainBinding.initializeLayout() {
        registerPermissionListener()
        addRecipeButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddDishActivity::class.java)
            this@MainActivity.startActivity(intent)
        }
    }

    private fun registerPermissionListener(){
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if(!it)Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT)
        }
    }

    private fun checkPermission(): Boolean {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
                    == PackageManager.PERMISSION_GRANTED -> {
                        return true
            }

            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                Toast.makeText(this,"We need your permission", Toast.LENGTH_SHORT)
                return false
            }

            else -> {
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                return false
            }

        }
    }

    companion object {
        const val DISH_TYPE_NAME = "Dish type name"
    }
}