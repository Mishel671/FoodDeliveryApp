package ru.michaeldzyuba.fooddeliveryapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import retrofit2.HttpException
import ru.michaeldzyuba.fooddeliveryapp.data.api.ApiService
import ru.michaeldzyuba.fooddeliveryapp.data.database.FoodDao
import ru.michaeldzyuba.fooddeliveryapp.data.mapper.mapToDB
import ru.michaeldzyuba.fooddeliveryapp.data.mapper.mapToItem
import ru.michaeldzyuba.fooddeliveryapp.data.mapper.mapToListDbModel
import ru.michaeldzyuba.fooddeliveryapp.data.staticdata.getAdsImage
import ru.michaeldzyuba.fooddeliveryapp.data.staticdata.getCategoriesList
import ru.michaeldzyuba.fooddeliveryapp.domain.AdItem
import ru.michaeldzyuba.fooddeliveryapp.domain.CategoryItem
import ru.michaeldzyuba.fooddeliveryapp.domain.FoodItem
import ru.michaeldzyuba.fooddeliveryapp.domain.FoodRepository
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

    override suspend fun loadFoodList(foodName: String): String? {
        if (isNetworkAvailable(application)) {
            try {
                val responseFood = apiService.getFoodListByName(query = foodName)
                val foodListDto = responseFood.menuItems
                val foodListDbModel = foodListDto.mapToListDbModel(foodName)
                foodDao.insertFoodList(foodListDbModel)
                return null
            } catch (throwable: Throwable) {
                if (throwable is HttpException) {
                    val code = throwable.code()
                    val error = throwable.localizedMessage
                    return "Status code $code. Error: $error"
                }
                return throwable.message
            }
        } else {
            return NO_INTERNET
        }

    }

    override fun loadCartFoodList(): LiveData<List<FoodItem>> {
        return Transformations
            .map(foodDao.getCardFoodList()) { foodList ->
                foodList.map { foodDbModel ->
                    foodDbModel.mapToItem()
                }
            }
    }

    override suspend fun changeFoodItem(foodItem: FoodItem) {
        val dbItem = foodItem.mapToDB()
        foodDao.insertFoodItem(dbItem)
    }

    override fun getFoodList(foodName: String) =
        Transformations
            .map(foodDao.getFoodList(foodName)) { foodList ->
                foodList.map { foodDbModel ->
                    foodDbModel.mapToItem()
                }
            }

    companion object {
        private const val NO_INTERNET = "No internet connection"
    }
}