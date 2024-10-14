package com.mehmetbaloglu.jetweatherforecast.data.model.LatLonInfo


import com.google.gson.annotations.SerializedName

data class LatLotInfoItem(
    @SerializedName("country")
    val country: String?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("name")
    val name: String?
)