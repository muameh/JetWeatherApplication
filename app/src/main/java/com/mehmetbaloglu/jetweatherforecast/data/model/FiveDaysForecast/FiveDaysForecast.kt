package com.mehmetbaloglu.jetweatherforecast.data.model.FiveDaysForecast


import com.google.gson.annotations.SerializedName

data class FiveDaysForecast(
    @SerializedName("city")
    val city: City?,
    @SerializedName("cnt")
    val cnt: Int?,
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("list")
    val listItem: kotlin.collections.List<ListItem?>?,
    @SerializedName("message")
    val message: Int?
)