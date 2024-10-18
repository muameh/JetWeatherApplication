package com.mehmetbaloglu.jetweatherforecast.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehmetbaloglu.jetweatherforecast.data.model.DataOrException
import com.mehmetbaloglu.jetweatherforecast.data.model.FavoriteCity
import com.mehmetbaloglu.jetweatherforecast.data.model.FiveDaysForecast.FiveDaysForecast
import com.mehmetbaloglu.jetweatherforecast.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    private val _favList = MutableStateFlow<List<FavoriteCity>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        getFavorites()
    }


    suspend fun getFiveDaysForecast(city: String): DataOrException<FiveDaysForecast, Boolean, Exception> {
        return repository.getFiveDaysForecast(cityQuery = city)
    }

    private fun getFavorites() = viewModelScope.launch(Dispatchers.IO) {
        repository.getFavorites()
            .distinctUntilChanged()
            .map { favCities ->
                favCities.sortedByDescending { it.cityName }
            }
            .collect { listOfFavs ->
                if (listOfFavs.isEmpty()) {
                    _favList.value = emptyList()
                    Log.d("TAG", "Empty favs")
                } else {
                    _favList.value = listOfFavs
                    Log.d("TAG", "${favList.value}")
                }
            }

    }

    fun insertFavorite(favoriteCity: FavoriteCity)
    = viewModelScope.launch(Dispatchers.IO) { repository.insertFavorite(favoriteCity) }

    fun deleteFavorite(favoriteCity: FavoriteCity)
    = viewModelScope.launch(Dispatchers.IO) { repository.deleteFavorite(favoriteCity) }

    fun updateFavorite(favoriteCity: FavoriteCity)
    = viewModelScope.launch(Dispatchers.IO) { repository.updateFavorite(favoriteCity) }


}