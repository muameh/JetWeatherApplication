package com.mehmetbaloglu.jetweatherforecast.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "favorite_cities_tbl")
data class FavoriteCity(
    @Nonnull
    @PrimaryKey
    @ColumnInfo(name = "city_name")
    val cityName: String,

    @ColumnInfo(name = "country_code")
    val countryCode: String
)