package com.mehmetbaloglu.jetweatherforecast.data.model.FiveDaysForecast


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int?
)