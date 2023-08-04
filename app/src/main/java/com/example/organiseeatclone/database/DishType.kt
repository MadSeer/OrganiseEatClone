package com.example.organiseeatclone.database

import androidx.annotation.DrawableRes
import com.example.organiseeatclone.R
import io.realm.kotlin.Deleteable
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class DishType():RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var name: String = ""
    @DrawableRes
    var icon: Int = R.drawable.icon_typedish_any
    var dishes: RealmList<Dish> = listOf<Dish>().toRealmList()

    fun toLocalModel() = DishTypeLocalModel(
        id = this.id,
        name = this.name,
        icon = this.icon,
        dishes = this.dishes.toList()
    )
}

data class DishTypeLocalModel(
    var id: ObjectId,
    var name: String,
    @DrawableRes
    var icon: Int,
    var dishes: List<Dish>?
)