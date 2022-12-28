package ru.michaeldzyuba.fooddeliveryapp.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {

    private lateinit var sharedPreferences: SharedPreferences
    private const val NAME_PREF = "NAME_PREF"
    private const val IMAGE_KEY = "IMAGE_KEY"
    private const val FULL_NAME_KEY = "FULL_NAME_KEY"
    private const val AGE_KEY = "AGE_KEY"

    fun create(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
    }

    fun saveImage(image: String) {
        sharedPreferences.edit()
            .putString(IMAGE_KEY, image)
            .commit()
    }

    fun getImage(): String {
        return sharedPreferences.getString(IMAGE_KEY, "") ?: ""
    }

    fun saveFullName(name: String) {
        sharedPreferences.edit()
            .putString(FULL_NAME_KEY, name)
            .commit()

    }

    fun getFullName(): String {
        return sharedPreferences.getString(FULL_NAME_KEY, "") ?: ""

    }

    fun saveAge(age:Int) {
        sharedPreferences.edit()
            .putInt(AGE_KEY, age)
            .commit()

    }

    fun getAge():Int {
        return sharedPreferences.getInt(AGE_KEY, -1)

    }

    fun clearAll(){
        sharedPreferences.edit().clear().commit()
    }
}