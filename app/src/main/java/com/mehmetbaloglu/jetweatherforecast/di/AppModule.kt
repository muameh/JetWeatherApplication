package com.mehmetbaloglu.jetweatherforecast.di

import android.content.Context
import androidx.room.Room
import com.mehmetbaloglu.jetweatherforecast.retrofit.WeatherApi
import com.mehmetbaloglu.jetweatherforecast.room.WeatherDB
import com.mehmetbaloglu.jetweatherforecast.room.WeatherDao
import com.mehmetbaloglu.jetweatherforecast.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherDao(weatherDB: WeatherDB): WeatherDao = weatherDB.weatherDao()

    @Provides
    @Singleton
    fun provideWeatherDB(@ApplicationContext context: Context): WeatherDB =
        Room
            .databaseBuilder(
                context,
                WeatherDB::class.java,
                "weather_db"
            )
            .fallbackToDestructiveMigration()
            .build()


}