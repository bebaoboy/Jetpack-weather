package com.bebaoboy.jetweatherapp.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyUnits(
    @SerialName("temperature_2m")
    val temperature2m: String = "", // °C
    @SerialName("time")
    val time: String = "", // unixtime
    @SerialName("weather_code")
    val weatherCode: String = "" // wmo code
)