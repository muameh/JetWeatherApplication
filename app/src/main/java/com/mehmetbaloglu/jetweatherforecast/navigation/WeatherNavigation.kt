package com.mehmetbaloglu.jetweatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mehmetbaloglu.jetweatherforecast.ui.screens.MainScreen
import com.mehmetbaloglu.jetweatherforecast.ui.screens.MainViewModel
import com.mehmetbaloglu.jetweatherforecast.ui.screens.WeatherSplashScreen


@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {

        composable(route = WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController = navController)
        }

        composable(route = WeatherScreens.MainScreen.name){
            val viewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController = navController,viewModel)
        }

        
    }
}

