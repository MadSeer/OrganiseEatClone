package com.example.organiseeatclone.di

import com.example.organiseeatclone.database.Database
import com.example.organiseeatclone.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val singleModules = module {
    singleOf(::Database)
}
val viewModelModules = module {
    viewModelOf(::MainViewModel)
}

fun provideModules() = listOf(singleModules, viewModelModules)