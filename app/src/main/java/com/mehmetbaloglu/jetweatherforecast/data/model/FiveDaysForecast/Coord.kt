package com.mehmetbaloglu.jetweatherforecast.data.model.FiveDaysForecast


import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?
)