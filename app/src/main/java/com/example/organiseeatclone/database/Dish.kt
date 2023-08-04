package com.example.organiseeatclone.database

import android.net.Uri
import android.os.Parcelable
import androidx.core.net.toUri
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.mongodb.kbson.ObjectId

class Dish : RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var name = ""
    var recipe = ""
    var ingridients: RealmList<String> = listOf<String>().toRealmList()
    var image: String? = null//R.drawable.image_dish_default
    var icon: String? = null//R.drawable.icon_typedish_any

    fun toLocalModel() = DishLocalModel(
        id = this.id.toString(),
        name = this.name,
        recipe = this.recipe,
        ingridients = this.ingridients.toList(),
        image = this.image!!.toUri(),
        icon = this.icon!!.toUri()
        )
}

data class DishLocalModel(
    val id: String,
    var name: String,
    var recipe: String,
    var ingridients: List<String>?,
    var image: Uri?,
    var icon: Uri?
)

@Parcelize
data class DishParcelizeLocalModel(
    val id: String,
    var name: String,
    var recipe: String,
    var ingridients: List<String>?,
    var image: Uri?,
    var icon: Uri?
) : Parcelable