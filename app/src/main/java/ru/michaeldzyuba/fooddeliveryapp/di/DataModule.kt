package ru.michaeldzyuba.fooddeliveryapp.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.michaeldzyuba.fooddeliveryapp.data.FoodRepositoryImpl
import ru.michaeldzyuba.fooddeliveryapp.data.api.ApiFactory
import ru.michaeldzyuba.fooddeliveryapp.data.api.ApiService
import ru.michaeldzyuba.fooddeliveryapp.data.database.AppDatabase
import ru.michaeldzyuba.fooddeliveryapp.data.database.FoodDao
import ru.michaeldzyuba.fooddeliveryapp.domain.FoodRepository

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindFoodRepository(impl: FoodRepositoryImpl): FoodRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideFoodDao(
            application: Application
        ): FoodDao {
            return AppDatabase.getInstance(application).foodDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}