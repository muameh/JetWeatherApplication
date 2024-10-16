package com.mehmetbaloglu.jetweatherforecast.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mehmetbaloglu.jetweatherforecast.R
import com.mehmetbaloglu.jetweatherforecast.data.DataOrException
import com.mehmetbaloglu.jetweatherforecast.data.model.FiveDaysForecast.FiveDaysForecast
import com.mehmetbaloglu.jetweatherforecast.data.model.FiveDaysForecast.ListItem
import com.mehmetbaloglu.jetweatherforecast.utils.Constants.BASE_ICON_URL
import com.mehmetbaloglu.jetweatherforecast.utils.Constants.ICON_SUFFIX
import com.mehmetbaloglu.jetweatherforecast.utils.Utils
import com.mehmetbaloglu.jetweatherforecast.widgets.WeatherAppBar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val weatherData = produceState<DataOrException<FiveDaysForecast, Boolean, Exception>>(
        initialValue = DataOrException(loading = true),
        producer = { value = mainViewModel.getFiveDaysForecast("ankara") }).value

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
    ) { innerPadding ->
        MainContent(
            modifier = Modifier.padding(innerPadding),
            fiveDaysForecast = fiveDaysForecast
        )
    }
}

@Composable
fun MainContent(fiveDaysForecast: FiveDaysForecast, modifier: Modifier = Modifier) {
    val currentWeatherItem = fiveDaysForecast.listItem?.get(0)
    val imageUrl = BASE_ICON_URL + currentWeatherItem?.weatherItem?.first()?.icon + ICON_SUFFIX


    Column(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
//            text = currentWeatherItem?.dtTxt.toString(),
            text = "Now",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )
        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0x55FFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(imageUrl = imageUrl)
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = currentWeatherItem?.main?.temp?.toInt().toString(),
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(text ="°C", fontSize = 24.sp)
                }

                Text(
                    text = currentWeatherItem?.weatherItem?.first()?.description.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontStyle = FontStyle.Italic
                )
            }

        }
        HumidityWindPressureRow(listItem = currentWeatherItem!!)
        HorizontalDivider(modifier=Modifier.padding(start = 15.dp, end = 15.dp, top = 0.dp, bottom = 10.dp))
        Text(text = "The Next Five Days", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)

        val uniqueDays = Utils.getUniqueDays(fiveDaysForecast.listItem)

        LazyColumn(
            modifier= Modifier.padding(2.dp)
        ) {
            items(fiveDaysForecast.listItem){
                if (it != null) {
                    if (it in uniqueDays){
                        CardDesignForUniqueDays(listItem = it)
                    }
                }
            }
        }


    }

}

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl),
        modifier = Modifier.size(80.dp),
        contentDescription = "weather icon"
    )
}

@Composable
fun HumidityWindPressureRow(listItem: ListItem){
    Row (
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row (modifier = Modifier.padding(4.dp)){
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = " ${listItem.main?.humidity.toString()}" + " %",
                fontSize = 14.sp
            )

        }
        Row (modifier = Modifier.padding(4.dp)){
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = " ${listItem.main?.pressure.toString()}" + " psi",
                fontSize = 14.sp
            )
        }
        Row (modifier = Modifier.padding(4.dp)){
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = " ${listItem.wind?.speed.toString()}" + " mph",
                fontSize = 14.sp
            )
        }

    }
}


@Preview
@Composable
fun CardDesignForUniqueDays(listItem: ListItem = ListItem(null,null,null,null,null,null,null,null,null)){
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(50.dp),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
    ) {
        val iconUrl = BASE_ICON_URL+ listItem.weatherItem?.get(0)?.icon.toString() + ICON_SUFFIX
        val formattedDate = Utils.formatDayOfWeek(listItem.dtTxt.toString())

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        ){
            Text(text = formattedDate)
            WeatherStateImage(imageUrl = iconUrl)
            Text(text = listItem.weatherItem?.get(0)?.main.toString())
            Text(text = listItem.main?.tempMax?.toInt().toString()+"°", color = Color.Blue)
            Text(text = listItem.main?.tempMin?.toInt().toString()+"°", color = Color.LightGray)
        }
    }
    HorizontalDivider(modifier=Modifier.padding(start = 15.dp, end = 15.dp, top = 0.dp, bottom = 10.dp))

}









