package com.example.organiseeatclone.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.organiseeatclone.core.BaseActivity
import com.example.organiseeatclone.core.RecipeAPI
import com.example.organiseeatclone.databinding.ActivitySearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class SearchActivity: BaseActivity<ActivitySearchBinding>() {
    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivitySearchBinding.inflate(inflater,container,false)

    override fun ActivitySearchBinding.initializeLayout() {
        activitySearchButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val dish = activitySearchText.text
                val recipeAPI = RecipeAPI()
                recipeAPI.url += dish
                recipeAPI.getResponse {
                    val response = JSONObject(it)
                    lifecycleScope.launch {

                        fun ingredientsResponseToString(list: List<String>): String {
                            var result = ""
                            list.forEach { result += it + "\n" }
                            return result
                        }

                        activitySearchReceiptNameText
                            .text = response
                            .getString("title")
                        val ingredientsArray = response
                            .getString("ingredients")
                            .split("|")
                        activitySearchIngredientsText
                            .text = ingredientsResponseToString(ingredientsArray)
                        activitySearchServingsText
                            .text = response.getString("servings")
                        activitySearchInstructionsText
                            .text = response.getString("instructions")
                    }
                }
            }
        }
    }
}