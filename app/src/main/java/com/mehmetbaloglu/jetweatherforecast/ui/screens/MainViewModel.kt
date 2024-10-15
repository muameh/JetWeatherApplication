package com.mehmetbaloglu.jetweatherforecast.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mehmetbaloglu.jetweatherforecast.data.DataOrException
import com.mehmetbaloglu.jetweatherforecast.data.model.FiveDaysForecast.FiveDaysForecast
import com.mehmetbaloglu.jetweatherforecast.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    val dataInViewModel: MutableState<DataOrException<FiveDaysForecast, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))


    suspend fun getFiveDaysForecast(city: String): DataOrException<FiveDaysForecast, Boolean, Exception> {
        return repository.getFiveDaysForecast(cityQuery = city)
    }


}