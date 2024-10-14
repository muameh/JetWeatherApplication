package com.mehmetbaloglu.jetweatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mehmetbaloglu.jetweatherforecast.ui.screens.MainScreen
import com.mehmetbaloglu.jetweatherforecast.ui.screens.WeatherSplashScreen


@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {

        composable(route = WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController = navController)
        }

        composable(route = WeatherScreens.MainScreen.name){
            MainScreen(navController = navController)
        }

        
    }
}

