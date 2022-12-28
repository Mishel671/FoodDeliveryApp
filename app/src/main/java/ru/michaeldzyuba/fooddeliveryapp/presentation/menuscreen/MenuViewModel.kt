package ru.michaeldzyuba.fooddeliveryapp.presentation.menuscreen

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.michaeldzyuba.fooddeliveryapp.domain.*
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val getAdsListUseCase: GetAdsListUseCase,
    private val getCategoriesListUseCase: GetCategoriesListUseCase,
    private val loadFoodListUseCase: LoadFoodListUseCase,
    private val getFoodListUseCase: GetFoodListUseCase,
    private val buyFoodItemUseCase: BuyFoodItemUseCase
) : ViewModel() {

    fun getAdsList() = getAdsListUseCase()

    fun getCategoriesList() = getCategoriesListUseCase()

    private val _errorLoad = MutableLiveData<String>()
    val errorLoad: LiveData<String>
        get() = _errorLoad

    private val requestName = MutableLiveData<CategoryItem>().apply {
        val categoryItem = getCategoriesList()[0]
        value = categoryItem
        viewModelScope.launch(Dispatchers.IO) {
            loadFoodListUseCase(categoryItem.queryValue)
        }
    }

    val foodList = Transformations.switchMap(requestName) {
        getFoodListUseCase(it.queryValue)
    }

    fun getActiveCategory() = requestName.value

    fun loadData(foodName: CategoryItem) {
        requestName.value = foodName
        viewModelScope.launch(Dispatchers.IO) {
            val errorMessage = loadFoodListUseCase(foodName.queryValue)
            if (errorMessage != null) {
                _errorLoad.postValue(errorMessage!!)
            }
        }
    }

    fun buyFoodItem(foodItem: FoodItem) {
        viewModelScope.launch(Dispatchers.IO) {
            buyFoodItemUseCase(foodItem.copy(isCart = true))
        }
    }

}