package com.mehmetbaloglu.jetweatherforecast.widgets

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mehmetbaloglu.jetweatherforecast.data.model.FavoriteCity
import com.mehmetbaloglu.jetweatherforecast.navigation.WeatherScreens
import com.mehmetbaloglu.jetweatherforecast.ui.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {},
    viewModel: MainViewModel = hiltViewModel()
) {
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(4.dp),
        shape = RoundedCornerShape(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge,
                    modifier =
                    Modifier
                        .padding(start = 30.dp, end = 30.dp, top = 8.dp, bottom = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent),
            actions = {
                if (isMainScreen) {
                    IconButton(onClick = { onAddActionClicked.invoke() }) {
                        Icon(
                            imageVector = Icons.Default.Search, contentDescription = "search icon"
                        )
                    }
                    IconButton(onClick = { showDialog.value = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "settings icon"
                        )
                    }
                } else {
                    Box {}
                }
            },
            navigationIcon = {
                if (icon != null) {
                    Icon(imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable { onButtonClicked.invoke() }
                    )
                }
                if (isMainScreen) {
                    val isAlreadyFavList = viewModel.favList.collectAsState().value.filter {
                        it.cityName == title.split(",")[0]
                    }

                    if (isAlreadyFavList.isEmpty()){
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "add icon",
                            tint = Color.Red.copy(alpha = 0.6f),
                            modifier =
                            Modifier
//                                .scale(0.9f)
                                .clickable {
                                    val cityName = title.split(",")[0]
                                    val countryCode = title.split(",")[1]
                                    viewModel.insertFavorite(
                                        FavoriteCity(
                                            cityName = cityName,
                                            countryCode = countryCode
                                        )
                                    )

                                    Toast
                                        .makeText(context, "Added to favorites", Toast.LENGTH_SHORT)
                                        .show()
                                }
                        )
                    } else Box { }
                }
            },
        )
    }

    // Dropdown menu positioned outside TopAppBar
    if (showDialog.value) {
        SettingsDropDownMenu(showDialog = showDialog, navController = navController)
    }
}

@Composable
fun SettingsDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    val expanded = showDialog
    val items = listOf("About", "Favorites", "Settings")
    val icons = listOf(
        Icons.Outlined.Info,
        Icons.Outlined.Favorite,
        Icons.Outlined.Settings
    )
    val routes = listOf(
        WeatherScreens.AboutScreen.name,
        WeatherScreens.FavoriteScreen.name,
        WeatherScreens.SettingsScreen.name
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 60.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)
        ) {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(
                    text = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = icons[index],
                                contentDescription = null,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = text,
                                fontWeight = FontWeight.W300
                            )
                        }
                    },
                    onClick = {
                        //expanded.value = false
                        navController.navigate(routes[index])
                    }
                )
            }
        }
    }
}
