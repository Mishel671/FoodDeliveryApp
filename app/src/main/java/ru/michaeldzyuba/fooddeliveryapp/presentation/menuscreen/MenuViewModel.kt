package ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.michaeldzyuba.fooddeliveryapp.domain.GetAdsListUseCase
import ru.michaeldzyuba.fooddeliveryapp.domain.GetCategoriesListUseCase
import ru.michaeldzyuba.fooddeliveryapp.domain.GetFoodListUseCase
import ru.michaeldzyuba.fooddeliveryapp.domain.LoadFoodListUseCase
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val getAdsListUseCase: GetAdsListUseCase,
    private val getCategoriesListUseCase: GetCategoriesListUseCase,
    private val loadFoodListUseCase: LoadFoodListUseCase,
    private val getFoodListUseCase: GetFoodListUseCase
) : ViewModel() {


    fun getAdsList() = getAdsListUseCase()

    fun getCategoriesList() = getCategoriesListUseCase()

    private val requestName = MutableLiveData<String>().apply {
        val queryValue = getCategoriesList()[0].queryValue
        value = queryValue
        viewModelScope.launch(Dispatchers.IO) {
            loadFoodListUseCase(queryValue)
        }
    }

    val foodList = Transformations.switchMap(requestName) {
        getFoodListUseCase(it)
    }

    fun loadData(foodName: String) {
        requestName.value = foodName
        viewModelScope.launch(Dispatchers.IO) {
            loadFoodListUseCase(foodName)
        }
    }

}