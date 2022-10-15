package ru.michaeldzyuba.fooddeliveryapp.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.michaeldzyuba.fooddeliveryapp.BuildConfig
import ru.michaeldzyuba.fooddeliveryapp.data.api.model.ResponseFood

interface ApiService {



    @GET(FOOD_LIST_ROUTE)
    suspend fun getFoodListByName(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("query") query: String,
        @Query("addMenuItemInformation") moreInfo: Boolean = true
    ): ResponseFood

    companion object {
        private const val FOOD_LIST_ROUTE = "search"
    }
}