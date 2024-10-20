package com.mehmetbaloglu.jetweatherforecast.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mehmetbaloglu.jetweatherforecast.data.model.FavoriteCity
import com.mehmetbaloglu.jetweatherforecast.data.model.Unit


@Database(entities = [FavoriteCity::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDB : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}