package com.example.model

import com.example.model.api.MealsWebService
import com.example.model.responses.MealResponse
import com.example.model.responses.MealsCategoriesResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class MealsRepository(private var webService: MealsWebService= MealsWebService()) {
    private var cachedMeal = listOf<MealResponse>()
    suspend fun getMeals(): MealsCategoriesResponses {
        val response = webService.getMeals()
        cachedMeal = response.categories
        return response
    }

    fun getMeal(id: String): MealResponse? {
         return cachedMeal.firstOrNull() {
            it.id == id
        }
    }

     companion object{
         @Volatile
          private var instance :MealsRepository? = null
         fun getInstance()= instance?: synchronized(this){
             instance?: MealsRepository().also { instance=it }
         }
     }
}
