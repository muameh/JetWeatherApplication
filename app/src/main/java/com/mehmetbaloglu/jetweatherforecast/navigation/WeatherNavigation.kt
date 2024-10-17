package com.mehmetbaloglu.jetweatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mehmetbaloglu.jetweatherforecast.ui.screens.MainScreen
import com.mehmetbaloglu.jetweatherforecast.ui.screens.MainViewModel
import com.mehmetbaloglu.jetweatherforecast.ui.screens.SearchScreen
import com.mehmetbaloglu.jetweatherforecast.ui.screens.WeatherSplashScreen


@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {

        composable(route = WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }

        composable(
            route = WeatherScreens.MainScreen.name + "/{city}",
            arguments = listOf(navArgument("city", builder = { NavType.StringType }))
        ) { navBack ->
            navBack.arguments?.getString("city").let { city ->
                val viewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, viewModel, city = city)
            }
        }

        composable(route = WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }


    }
}

