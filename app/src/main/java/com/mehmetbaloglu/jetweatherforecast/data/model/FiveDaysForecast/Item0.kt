package com.mehmetbaloglu.jetweatherforecast.data.model.FiveDaysForecast


import com.google.gson.annotations.SerializedName

data class Item0(
    @SerializedName("clouds")
    val clouds: Clouds?,
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("dt_txt")
    val dtTxt: String?,
    @SerializedName("main")
    val main: Main?,
    @SerializedName("pop")
    val pop: Int?,
    @SerializedName("sys")
    val sys: Sys?,
    @SerializedName("visibility")
    val visibility: Int?,
    @SerializedName("weather")
    val weather: List<Weather?>?,
    @SerializedName("wind")
    val wind: Wind?
)