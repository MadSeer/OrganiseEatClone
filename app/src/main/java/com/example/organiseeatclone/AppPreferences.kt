package com.example.organiseeatclone

import android.content.SharedPreferences

class AppPreferences constructor(private val sharedPreferences: SharedPreferences) {

    companion object{
        private val IS_FIRST_RUN = "isFirstRun"
    }
    fun isFirstRun() = sharedPreferences.getBoolean(IS_FIRST_RUN,true)
    fun setAnyRun() = sharedPreferences.edit().putBoolean(IS_FIRST_RUN,false).apply()

}