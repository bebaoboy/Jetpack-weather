package com.bebaoboy.jetweatherapp.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiCurrentWeatherModel(
    @SerialName("interval")
    val interval: Int = 0, // 900
    @SerialName("is_day")
    val isDay: Int = 0, // 1
    @SerialName("rain")
    val rain: Double = 0.0, // 0.00
    @SerialName("relative_humidity_2m")
    val relativeHumidity2m: Int = 0, // 57
    @SerialName("temperature_2m")
    val temperature2m: Double = 0.0, // 17.2
    @SerialName("time")
    val time: Int = 0, // 1720165500
    @SerialName("weather_code")
    val weatherCode: Int = 0, // 2
    @SerialName("wind_direction_10m")
    val windDirection10m: Int = 0, // 227
    @SerialName("wind_speed_10m")
    val windSpeed10m: Double = 0.0 // 8.9
)