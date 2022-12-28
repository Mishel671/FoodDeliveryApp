package ru.michaeldzyuba.fooddeliveryapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDao {

    @Query("SELECT * FROM food_list WHERE requestName=:requestName ORDER BY id")
    fun getFoodList(requestName: String): LiveData<List<FoodItemDbModel>>

    @Query("SELECT * FROM food_list WHERE isCart=:isCart ORDER BY id")
    fun getCardFoodList(isCart: Boolean? = true): LiveData<List<FoodItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodList(foodList: List<FoodItemDbModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodItem(foodItem: FoodItemDbModel)
}