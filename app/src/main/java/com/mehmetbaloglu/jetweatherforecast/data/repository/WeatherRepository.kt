package com.mehmetbaloglu.jetweatherforecast.data.repository

import com.mehmetbaloglu.jetweatherforecast.data.DataOrException
import com.mehmetbaloglu.jetweatherforecast.data.model.FiveDaysForecast.FiveDaysForecast
import com.mehmetbaloglu.jetweatherforecast.retrofit.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getFiveDaysForecast(cityQuery: String): DataOrException<FiveDaysForecast, Boolean, Exception> {
        return try {
            DataOrException(data = api.getFiveDaysForecast(cityQuery))
        } catch (e: Exception) {
            DataOrException(e = e)
        }
    }
}