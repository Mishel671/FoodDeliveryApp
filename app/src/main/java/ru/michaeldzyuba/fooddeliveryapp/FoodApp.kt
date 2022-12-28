package ru.michaeldzyuba.fooddeliveryapp

import android.app.Application
import ru.michaeldzyuba.fooddeliveryapp.di.DaggerApplicationComponent
import ru.michaeldzyuba.fooddeliveryapp.utils.AppPreferences

class FoodApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        AppPreferences.create(this)
    }
}