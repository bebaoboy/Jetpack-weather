package com.bebaoboy.jetweatherapp.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentUnits(
    @SerialName("interval")
    val interval: String = "", // seconds
    @SerialName("is_day")
    val isDay: String = "",
    @SerialName("rain")
    val rain: String = "", // mm
    @SerialName("relative_humidity_2m")
    val relativeHumidity2m: String = "", // %
    @SerialName("temperature_2m")
    val temperature2m: String = "", // °C
    @SerialName("time")
    val time: String = "", // unixtime
    @SerialName("weather_code")
    val weatherCode: String = "", // wmo code
    @SerialName("wind_direction_10m")
    val windDirection10m: String = "", // °
    @SerialName("wind_speed_10m")
    val windSpeed10m: String = "" // km/h
)