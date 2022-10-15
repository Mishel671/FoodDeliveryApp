package ru.michaeldzyuba.fooddeliveryapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.michaeldzyuba.fooddeliveryapp.BuildConfig

object ApiFactory {

    private val SERVER_URL = BuildConfig.SERVER_URL


    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_URL)
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}