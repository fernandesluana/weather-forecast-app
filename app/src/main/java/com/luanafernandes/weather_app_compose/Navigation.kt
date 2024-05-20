package com.luanafernandes.weather_app_compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.luanafernandes.weather_app_compose.feature_weather_forecast.presentation.location.LocationScreen
import com.luanafernandes.weather_app_compose.feature_weather_forecast.presentation.weather.WeatherScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            LocationScreen(navController)
        }
        composable("weather/{location_key}/{name}/{country}", arguments = listOf(
            navArgument("location_key") {
                type = NavType.StringType
            },
            navArgument("name") {
                type = NavType.StringType
            },
            navArgument("country") {
                type = NavType.StringType
            }
        )) {backStackEntry ->
            WeatherScreen(
                locationKey = backStackEntry.arguments?.getString("location_key") ?: "",
                locationName = backStackEntry.arguments?.getString("name") ?: "",
                country = backStackEntry.arguments?.getString("country") ?: "",
                navController = navController

            )
        }
    }
}