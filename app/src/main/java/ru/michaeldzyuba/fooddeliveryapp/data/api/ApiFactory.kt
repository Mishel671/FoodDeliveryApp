package ru.michaeldzyuba.fooddeliveryapp.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.michaeldzyuba.fooddeliveryapp.BuildConfig

object ApiFactory {

    private val SERVER_URL = BuildConfig.SERVER_URL

    private val interceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build();

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(SERVER_URL)
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}