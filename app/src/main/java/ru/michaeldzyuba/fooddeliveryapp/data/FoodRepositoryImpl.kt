package ru.michaeldzyuba.fooddeliveryapp.data

import android.app.Application
import androidx.lifecycle.Transformations
import ru.michaeldzyuba.fooddeliveryapp.data.api.ApiService
import ru.michaeldzyuba.fooddeliveryapp.data.database.FoodDao
import ru.michaeldzyuba.fooddeliveryapp.data.mapper.mapToItem
import ru.michaeldzyuba.fooddeliveryapp.data.mapper.mapToListDbModel
import ru.michaeldzyuba.fooddeliveryapp.data.static.getAdsImage
import ru.michaeldzyuba.fooddeliveryapp.data.static.getCategoriesList
import ru.michaeldzyuba.fooddeliveryapp.domain.AdItem
import ru.michaeldzyuba.fooddeliveryapp.domain.CategoryItem
import ru.michaeldzyuba.fooddeliveryapp.domain.FoodRepository
import ru.michaeldzyuba.fooddeliveryapp.utils.isNetworkAvailable
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val application: Application,
    private val foodDao: FoodDao,
    private val apiService: ApiService
) : FoodRepository {

    override fun getCategories(): List<CategoryItem> {
        return getCategoriesList()
    }

    override fun getAds(): List<AdItem> {
        return getAdsImage()
    }

    override suspend fun loadFoodList(foodName: String) {
        if (isNetworkAvailable(application)) {
            try {
                val responseFood = apiService.getFoodListByName(query = foodName)
                val foodListDto = responseFood.foodList
                val foodListDbModel = foodListDto.mapToListDbModel(foodName)
                foodDao.insertFoodList(foodListDbModel)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getFoodList(foodName: String) =
        Transformations
            .map(foodDao.getFoodList(foodName)) { foodList ->
                foodList.map { foodDbModel ->
                    foodDbModel.mapToItem()
                }
            }
}