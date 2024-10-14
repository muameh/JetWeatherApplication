package com.mehmetbaloglu.jetweatherforecast.data.model.FiveDaysForecast


import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("pod")
    val pod: String?
)