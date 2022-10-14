package ru.michaeldzyuba.fooddeliveryapp

import android.app.Application
import ru.michaeldzyuba.fooddeliveryapp.di.DaggerApplicationComponent

class FoodApp:Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}