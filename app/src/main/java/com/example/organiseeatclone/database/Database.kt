package com.example.organiseeatclone.database

import android.net.Uri
import com.example.organiseeatclone.R
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.toRealmList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Database {
    val config = RealmConfiguration.create(schema = setOf(DishType::class, Dish::class))
    val realm: Realm = Realm.open(config)

    fun addDishType(
        name: String,
        icon: Int
    ) {
        realm.writeBlocking {
            copyToRealm(DishType().apply {
                this.name = name
                this.icon = icon
            })
        }
    }

    fun setDefaultDishTypes() {
        realm.writeBlocking {
            copyToRealm(DishType().apply {
                this.name = "Salads"
                this.icon = R.drawable.icon_typedish_salads
            })
            copyToRealm(DishType().apply {
                this.name = "Soups"
                this.icon = R.drawable.icon_typedish_soup
            })
            copyToRealm(DishType().apply {
                this.name = "Breakfasts"
                this.icon = R.drawable.item_typedish_breakfasts
            })
            copyToRealm(DishType().apply {
                this.name = "Bird"
                this.icon = R.drawable.item_typedish_bird
            })
            copyToRealm(DishType().apply {
                this.name = "Fish"
                this.icon = R.drawable.item_typedish_fish
            })
            copyToRealm(DishType().apply {
                this.name = "Meat"
                this.icon = R.drawable.icon_typedish_meat
            })
        }
    }

    fun addDish(
        type: DishType,
        name: String,
        recipe: String,
        ingridients: List<String>,
        image: Uri?,
        icon: Uri?,
    ) = CoroutineScope(Dispatchers.IO).launch {
        realm.writeBlocking {
            val dishType = realm.query<DishType>("name == $0", type.name)
                .find()
                .first()
            val dish = Dish().apply {
                this.name = name
                this.icon = icon.toString()
                this.image = image.toString()
                this.recipe = recipe
                this.ingridients = ingridients.toRealmList()
            }
            var currentDishes = dishType!!.dishes.toMutableList()
            currentDishes.add(dish)
            /*dishType!!.dishes.add(dish)*/
            dishType.dishes = currentDishes.toRealmList()
        }
    }


    fun getDishTypes(): List<DishTypeLocalModel> {
        return realm.query<DishType>().find().map { it.toLocalModel() }
    }

}