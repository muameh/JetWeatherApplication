package com.mehmetbaloglu.jetweatherforecast.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mehmetbaloglu.jetweatherforecast.data.model.Unit
import com.mehmetbaloglu.jetweatherforecast.ui.viewmodels.MainViewModel
import com.mehmetbaloglu.jetweatherforecast.widgets.WeatherAppBar
import kotlinx.coroutines.delay

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val unitToggleState = remember { mutableStateOf(false) }
    val choiceFromDB = viewModel.unitList.collectAsState().value

    // Eğer DB'den bir seçim varsa onu kullan, yoksa Imperial (F) seçimini default yap
    val defaultChoice = if (choiceFromDB.isNotEmpty()) {
        if (choiceFromDB[0].unit == "Imperial (F)") {
            unitToggleState.value = true
            "Imperial (F)"
        } else {
            unitToggleState.value = false
            "Metric (C)"
        }
    } else {
        "Imperial (F)"
    }

    val choiceState = remember { mutableStateOf(defaultChoice) }

    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Settings",
                navController = navController,
                icon = Icons.Default.ArrowBack,
                isMainScreen = false,
                onButtonClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Change Units of Measurement",
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                IconToggleButton(
                    checked = unitToggleState.value,
                    onCheckedChange = {
                        unitToggleState.value = it
                        choiceState.value =
                            if (it) "Imperial (F)" else "Metric (C)"
                    },
                    content = {
                        if (unitToggleState.value) {
                            Text("Fahrenheit (F)")

                        } else {
                            Text("Celsius (C)")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clip(shape = RectangleShape)
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(Color.Magenta.copy(alpha = 0.4f))
                )
                Button(
                    modifier = Modifier
                        .padding(3.dp)
                        .align(CenterHorizontally),
                    onClick = {
                        viewModel.deleteAllUnits()
                        viewModel.insertUnit(Unit(choiceState.value))

                    },
                    shape = RoundedCornerShape(34.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEFBE42))
                ) {
                    Text(text = "Save", modifier = Modifier.padding(4.dp), color = Color.White)
                }
            }
        }
    }
}
