package com.mehmetbaloglu.jetweatherforecast.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mehmetbaloglu.jetweatherforecast.data.model.FavoriteCity

@Database(entities = [FavoriteCity::class], version = 1, exportSchema = false)
abstract class WeatherDB : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}