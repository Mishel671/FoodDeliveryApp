package ru.michaeldzyuba.fooddeliveryapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.michaeldzyuba.fooddeliveryapp.data.api.model.ResponseFood

@Dao
interface FoodDao {

    @Query("SELECT * FROM food_list WHERE requestName=:requestName ORDER BY id")
    fun getFoodList(requestName: String): LiveData<List<FoodItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodList(foodList: List<FoodItemDbModel>)
}