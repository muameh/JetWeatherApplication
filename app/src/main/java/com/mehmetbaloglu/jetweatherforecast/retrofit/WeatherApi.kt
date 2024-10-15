package com.mehmetbaloglu.jetweatherforecast.retrofit


import com.mehmetbaloglu.jetweatherforecast.data.model.FiveDaysForecast.FiveDaysForecast
import com.mehmetbaloglu.jetweatherforecast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {


    //5 günlük hava tahmini alır
    @GET("forecast")
    suspend fun getFiveDaysForecast(
        @Query("q") query : String,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = Constants.API_KEY
    ) : FiveDaysForecast


}