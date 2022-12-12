package com.example.mealzapp.ui.meals


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.model.MealsRepository
import com.example.model.responses.MealResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MealsCategoriesViewModel(
    // we want viewModel to depend on models(repository)
    private val repository: MealsRepository= MealsRepository.getInstance()): ViewModel() {
     val mealState: MutableState<List<MealResponse>> = mutableStateOf(emptyList<MealResponse>())

    // we use our own coroutine scope
    private val mealJob = Job()
    override fun onCleared() {
        super.onCleared()
        mealJob.cancel()
    }

    init{
        val scope = CoroutineScope(mealJob+Dispatchers.IO)
        scope.launch {
            val meals= getMeals()
            mealState.value= meals
        }
    }
           private suspend fun getMeals():List<MealResponse> {
             return  repository.getMeals().categories
        }


}