package com.example.organiseeatclone.database

import androidx.annotation.DrawableRes
import com.example.organiseeatclone.R
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Dish : RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var name = ""
    var recipe = ""
    var ingridients: RealmList<String> = listOf<String>().toRealmList()

    @DrawableRes
    var image: Int = R.drawable.image_dish_default

    @DrawableRes
    var icon: Int = R.drawable.icon_typedish_any

    fun toLocalModel() = DishSerialLocalModel(
        id = this.id,
        name = this.name,
        recipe = this.recipe,
        ingridients = this.ingridients.toList(),
        image = this.image,
        icon = this.icon
        )
}

data class DishSerialLocalModel(
    val id: ObjectId,
    var name: String,
    var recipe: String,
    var ingridients: List<String>?,
    @DrawableRes
    var image: Int,
    @DrawableRes
    var icon: Int
)