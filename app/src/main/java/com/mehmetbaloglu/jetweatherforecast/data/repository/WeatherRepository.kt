package com.mehmetbaloglu.jetweatherforecast.data.repository

import com.mehmetbaloglu.jetweatherforecast.data.model.DataOrException
import com.mehmetbaloglu.jetweatherforecast.data.model.FavoriteCity
import com.mehmetbaloglu.jetweatherforecast.data.model.FiveDaysForecast.FiveDaysForecast
import com.mehmetbaloglu.jetweatherforecast.retrofit.WeatherApi
import com.mehmetbaloglu.jetweatherforecast.room.WeatherDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi,
    private val weatherDao: WeatherDao
) {

    suspend fun getFiveDaysForecast(cityQuery: String): DataOrException<FiveDaysForecast, Boolean, Exception> {
        return try {
            DataOrException(data = api.getFiveDaysForecast(cityQuery))
        } catch (e: Exception) {
            DataOrException(e = e)
        }
    }

    fun getFavorites(): Flow<List<FavoriteCity>> = weatherDao.getFavorites()
    suspend fun insertFavorite(favoriteCity: FavoriteCity) = weatherDao.insertFavorite(favoriteCity)
    suspend fun updateFavorite(favoriteCity: FavoriteCity) = weatherDao.updateFavorite(favoriteCity)
    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()
    suspend fun deleteFavorite(favoriteCity: FavoriteCity) = weatherDao.deleteFavorite(favoriteCity)
    suspend fun getFavById(city: String): FavoriteCity = weatherDao.getFavById(city)

}