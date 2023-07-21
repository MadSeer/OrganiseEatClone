package com.example.organiseeatclone.database

import android.preference.PreferenceManager
import com.example.organiseeatclone.R
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.toRealmList

class Database {
    val config = RealmConfiguration.create(schema = setOf(DishType::class,Dish::class))
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

    fun setDefaultDishTypes(){
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
        image: Int,
        icon: Int,
    ) {
        realm.writeBlocking {
            realm.query<DishType>("id == $0", type.id).first().find().apply {
                this?.dishes?.add(Dish().apply {
                    this.name = name
                    this.recipe = recipe
                    this.ingridients = ingridients.toRealmList()
                    this.image = image
                    this.icon = icon
                })
            }
        }
    }



    fun getDishTypes(): List<DishTypeLocalModel> {
        return realm.query<DishType>().find().map { it.toLocalModel() }
    }

    /*fun addtestdata(){
       realm.writeBlocking {
           copyToRealm(DishType().apply {
               this.name = "ZAlupa"
           })
           copyToRealm(DishType().apply {
               this.name = "ZAlupa2"
           })
           copyToRealm(DishType().apply {
               this.name = "ZAlupa1231"
           })
           copyToRealm(DishType().apply {
               this.name = "ZAlupa6654"
           })
       }
   }*/
}