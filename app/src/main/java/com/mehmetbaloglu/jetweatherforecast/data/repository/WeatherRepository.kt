package com.mehmetbaloglu.jetweatherforecast.data.repository

import com.mehmetbaloglu.jetweatherforecast.data.model.DataOrException
import com.mehmetbaloglu.jetweatherforecast.data.model.FavoriteCity
import com.mehmetbaloglu.jetweatherforecast.data.model.FiveDaysForecast.FiveDaysForecast
import com.mehmetbaloglu.jetweatherforecast.data.model.Unit
import com.mehmetbaloglu.jetweatherforecast.retrofit.WeatherApi
import com.mehmetbaloglu.jetweatherforecast.room.WeatherDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi,
    private val weatherDao: WeatherDao
) {

    //it is related to the network request
    suspend fun getFiveDaysForecast(cityQuery: String, units: String = "metric")
            : DataOrException<FiveDaysForecast, Boolean, Exception> {
        return try {
            DataOrException(data = api.getFiveDaysForecast(cityQuery, units = units))
        } catch (e: Exception) {
            DataOrException(e = e)
        }
    }

    //it is related to the room database
    fun getFavorites(): Flow<List<FavoriteCity>> = weatherDao.getFavorites()
    suspend fun insertFavorite(favoriteCity: FavoriteCity) = weatherDao.insertFavorite(favoriteCity)
    suspend fun updateFavorite(favoriteCity: FavoriteCity) = weatherDao.updateFavorite(favoriteCity)
    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()
    suspend fun deleteFavorite(favoriteCity: FavoriteCity) = weatherDao.deleteFavorite(favoriteCity)
    suspend fun getFavById(city: String): FavoriteCity = weatherDao.getFavById(city)

    fun getUnits(): Flow<List<Unit>> = weatherDao.getUnits()
    suspend fun insertUnit(unit: Unit) = weatherDao.insertUnit(unit)
    suspend fun updateUnit(unit: Unit) = weatherDao.updateUnit(unit)
    suspend fun deleteAllUnits() = weatherDao.deleteAllUnits()
    suspend fun deleteUnit(unit: Unit) = weatherDao.deleteUnit(unit)

}