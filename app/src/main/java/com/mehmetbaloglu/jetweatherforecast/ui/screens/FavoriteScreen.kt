package com.mehmetbaloglu.jetweatherforecast.ui.screens


import android.graphics.fonts.FontStyle
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mehmetbaloglu.jetweatherforecast.data.model.FavoriteCity
import com.mehmetbaloglu.jetweatherforecast.navigation.WeatherScreens
import com.mehmetbaloglu.jetweatherforecast.ui.viewmodels.MainViewModel
import com.mehmetbaloglu.jetweatherforecast.widgets.WeatherAppBar

@Composable
fun FavoriteScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val favCitiesList = mainViewModel.favList.collectAsState().value


    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Favorite Cities",
                icon = Icons.Default.ArrowBack,
                navController = navController,
                isMainScreen = false,
                onButtonClicked = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            LazyColumn {
                items(favCitiesList){ favCity ->
                    CityRow(
                        //navController.navigate(WeatherScreens.MainScreen.name+"/$defaultCity")
                        city = favCity,
                        navigationFunc = {navController.navigate(WeatherScreens.MainScreen.name+"/$it")}
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CityRow(
    city: FavoriteCity = FavoriteCity("Ankara","TR"),
    navigationFunc: (String) -> Unit = {},
    viewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            //.height(50.dp)
            .wrapContentHeight()
            .padding(start = 10.dp, end = 10.dp)
            .clickable { navigationFunc.invoke(city.cityName) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .weight(1f),
                text = city.cityName
            )
            Spacer(modifier = Modifier.width(30.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = city.countryCode,
                fontWeight = FontWeight.Bold,
                color = Color.Red.copy(alpha = 0.6f)
            )
            IconButton(
                onClick = {
                    viewModel.deleteFavorite(city)
                    Toast.makeText(context,"${city.cityName} Deleted from Favorites",Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Icon")
            }

        }
    }

}
