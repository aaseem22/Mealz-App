package com.example.mealzapp.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealzapp.ui.details.MealDetailViewModel
import com.example.mealzapp.ui.details.MealDetailsScreen
import com.example.mealzapp.ui.meals.MealsCategoriesScreen

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                   FoodApp()
                }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
private fun FoodApp(){
        val navController= rememberNavController()
    NavHost(navController , startDestination ="destination_meals_list" ){
        composable(route = "destination_meals_list"){
            MealsCategoriesScreen() { navigateMealId->
                navController.navigate("destination_meals_list/$navigateMealId")
            }
        }

        composable(route="destination_meals_list/{meal_category_id}",
        arguments = listOf(navArgument("meal_category_id"){
            type= NavType.StringType
        })
        ){
            val viewModel: MealDetailViewModel= viewModel()
            MealDetailsScreen(viewModel.mealState.value)
        }

    }
}

