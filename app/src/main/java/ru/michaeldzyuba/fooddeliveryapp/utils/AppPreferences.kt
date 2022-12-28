package ru.michaeldzyuba.fooddeliveryapp.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {

    private lateinit var sharedPreferences: SharedPreferences
    private const val NAME_PREF = "NAME_PREF"
    private const val IMAGE_KEY = "IMAGE_KEY"

    fun create(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
    }

    fun saveImage(image: String) {
        sharedPreferences.edit()
            .putString(IMAGE_KEY, image)
            .commit()
    }

    fun getImage():String {
        return sharedPreferences.getString(IMAGE_KEY, "")?: ""
    }
}