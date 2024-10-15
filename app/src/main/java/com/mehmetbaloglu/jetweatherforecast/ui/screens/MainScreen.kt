package com.mehmetbaloglu.jetweatherforecast.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mehmetbaloglu.jetweatherforecast.data.DataOrException
import com.mehmetbaloglu.jetweatherforecast.data.model.FiveDaysForecast.FiveDaysForecast
import com.mehmetbaloglu.jetweatherforecast.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel = hiltViewModel()) {

    val weatherData = produceState<DataOrException<FiveDaysForecast, Boolean, Exception>>(
        initialValue = DataOrException(loading = true),
        producer = { value = mainViewModel.getFiveDaysForecast("ankara") }
    ).value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        MainScaffold(weatherData.data!!, navController)
    }
}

@Composable
fun MainScaffold(fiveDaysForecast: FiveDaysForecast, navController: NavController) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "${fiveDaysForecast.city?.name.toString()}, ${fiveDaysForecast.city?.country}",
                elevation = 4.dp
            )
        }
    )
    { innerPadding ->
        MainContent(
            modifier = Modifier.padding(innerPadding),
            fiveDaysForecast = fiveDaysForecast
        )
    }
}

@Composable
fun MainContent(fiveDaysForecast: FiveDaysForecast, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Nov 29",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )

        Surface(
            modifier= Modifier.padding(4.dp).size(200.dp),
            shape = CircleShape
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //image
                Text(
                    text = "34",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "Snow",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontStyle = FontStyle.Italic
                )
            }

        }

    }

}




