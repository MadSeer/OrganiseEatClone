package com.example.organiseeatclone.di

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.organiseeatclone.AppPreferences
import com.example.organiseeatclone.database.Database
import com.example.organiseeatclone.ui.addDish.AddDishViewModel
import com.example.organiseeatclone.ui.addDish.ChooseCategory.ChooseCategoryViewModel
import com.example.organiseeatclone.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val singleModules = module {
    singleOf(::Database)
    singleOf(::AppPreferences)
    singleOf(::provideSharedPreferences)
}
val viewModelModules = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::AddDishViewModel)
    viewModelOf(::ChooseCategoryViewModel)
}

fun provideSharedPreferences(context: Context): SharedPreferences =
    context.getSharedPreferences("organise_eat_clone_preferences", Activity.MODE_PRIVATE)

fun provideModules() = listOf(singleModules, viewModelModules)