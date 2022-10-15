package ru.michaeldzyuba.fooddeliveryapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen.MenuFragment

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: MenuFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}