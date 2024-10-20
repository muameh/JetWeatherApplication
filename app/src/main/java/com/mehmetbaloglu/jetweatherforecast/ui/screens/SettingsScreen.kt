package com.mehmetbaloglu.jetweatherforecast.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mehmetbaloglu.jetweatherforecast.ui.viewmodels.MainViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {}
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {

        }

    }

}
